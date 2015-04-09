package main;

import java.io.IOException;

import book.Library;
import rdfFetch.RDFData;
import content.ContentExtraction;
import content.ContentExtractionJATE;
import hmm.RunViterbi;

import hmm.CustomHMMRun;

public class HelloRDF {
	
	
	public static void main(String[] args) {
		
		/**
		 *  Reading RDF data
		 */
		//Library lib = new Library();
		//RDFData.readRDF("data/sweto_nano.rdf", lib);
		
		/**
		 * Printing RDF data
		 */
		// lib.print();
		
		/**
		 * Tagging the content
		 */
		
		/*try {
			new ContentExtraction().run("data/test_book.txt", "data/stoplist_en.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		try {
			new ContentExtractionJATE().run("data/test_preface.txt", "/home/krypten/jate_1.11/nlp_resources/bnc_unifrqs.normal", "data");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/**
		 * Testing HMM Working
		 */
		
		/*
		String filename = "data/topics.data";
		try {
			RunViterbi.start(filename);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/**
		 * Running Custom HMM
		 */
		CustomHMMRun.start();
		
	}
}

/*
 * Extra Code for learning and understanding Jena API
 */
/*

	//System.out.println(BookChapter.resourceBookChapter);
	// extract 
	//Selector selector = new SimpleSelector(null,null,"http://lsdis.cs.uga.edu/projects/semdis/opus#Book_Chapter"));

//System.out.println(obj.getSubject().toString() + " : "  + obj.getPredicate().toString() + " : " + obj.getObject().toString());

	//printModelResource(obj.getSubject(), model.getResource(BookChapter.bookTitleURI), model);

	
	ResIterator iter = model.listSubjects();
	
	if (iter.hasNext()) {
	    System.out.println("The database contains vcards for:");
	    while (iter.hasNext()) {
	        System.out.println("  " + iter.nextResource().getURI() );
	    }
	} else {
	    System.out.println("No vcards were found in the database");
	} 
	
	// write it to standard out
	//model.write(System.out);
*/