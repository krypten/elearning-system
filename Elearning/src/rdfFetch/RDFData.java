package rdfFetch;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import book.Book;
import book.BookChapter;
import book.Library;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class RDFData {
	public static void extractModelResource(Resource subject, Model model, HashMap<String, String> map) {
		StmtIterator stmtIter = model.listStatements();
		
		if (stmtIter.hasNext()) {
		    while (stmtIter.hasNext()) {
		    	Statement obj = stmtIter.nextStatement();
		    	if (obj.getSubject().equals(subject)) {
		    		map.put(obj.getPredicate().toString(), obj.getObject().toString());
		    	}
		    }
		} else {
			System.out.println("Resource not found !!! ");
		}
	}
	
	public static BookChapter extractModelChapter(Resource subject, Model model) {
		BookChapter chapter = new BookChapter();
		extractModelResource(subject, model, chapter.tags);
		return chapter;
	}

	public static HashMap<String, String> extractModelBookTags(Resource subject, Model model) {
		Book book = new Book();
		extractModelResource(subject, model, book.tags);
		return book.getTags();
	}
	
	public static void readRDF(String inputFileName, Library lib) {
		Model model = ModelFactory.createDefaultModel();
	
		 // use the FileManager to find the input file
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		    throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}
	
		// read the RDF/XML file
		model.read(in, null);
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Extracting chapters and inserting in library
		Selector selector = new SimpleSelector(null, null, model.getResource(BookChapter.chapterURI));
		StmtIterator stmtIter = model.listStatements(selector);

		
		if (stmtIter.hasNext()) {
		    while (stmtIter.hasNext()) {
		    	Statement obj = stmtIter.nextStatement();

		    	BookChapter chapter = extractModelChapter(obj.getSubject(), model);
		    	lib.insert(chapter);
		    }
		} else {
		    System.out.println("No chapters were found in the database");
		}


		// Extracting Book and inserting in library
		Selector bookSelector = new SimpleSelector(null, null, model.getResource(Book.bookURI));
		stmtIter = model.listStatements(bookSelector);
		
		if (stmtIter.hasNext()) {
		    while (stmtIter.hasNext()) {
		    	Statement obj = stmtIter.nextStatement();
		    	
		    	HashMap<String, String> tags = extractModelBookTags(obj.getSubject(), model);
		    	if (tags.containsKey(Book.bookTitleURI)) {
		    		String bookTitle = tags.get(Book.bookTitleURI);
		    		Book book = lib.getBookByTitle(bookTitle);
		    		book.getTags().putAll(tags);
		    		book.setLanguage(tags.get(Book.bookLanguageURI));
		    		lib.insert(book);
		    	}
		    }
		} else {
		    System.out.println("No books were found in the database");
		}
	}
}

/* 
	SPARQL :: Not working currently

String queryStr = "PREFIX opus: <http://lsdis.cs.uga.edu/projects/semdis/opus>"

				+ " SELECT ?varX WHERE { "
				+ "?varX  opus:#book_title " + subject.toString() + " . "
				+ " } limit 2";

Query query = QueryFactory.create(queryStr) ;

try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
	//Iterator<QuerySolution> results = qexec.execSelect() ;
	ResultSet results = qexec.execSelect();

	// Output query results	
	ResultSetFormatter.out(System.out, results, query);
	
	//for ( ; results.hasNext() ; ) {
   //	QuerySolution soln = results.next() ;
   //	System.out.println("QUERY SOLUTION  : " + soln.toString());
   //}
}
*/

