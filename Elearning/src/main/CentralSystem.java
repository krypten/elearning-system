package main;

import book.Library;
import rdfFetch.RDFData;
import engine.AdaptiveLPSEngine;
import authoring.LearningRepository;

public class CentralSystem {
	
	public LearningRepository repo = new LearningRepository();
	public AdaptiveLPSEngine engine = new AdaptiveLPSEngine();
	
	public void init() {
		/**
		 *  Reading RDF data
		 */
		Library lib = new Library();
		RDFData.readRDF("data/custom_nano.rdf", lib);
		
		/**
		 * Printing RDF data
		 */
		// lib.print();
		
		/*
		 * Generating the Learning Repository
		 */		
		repo.insert(lib);
		repo.print();

		/*
		 * Welcome Message
		 */
		System.out.println("Welcome to Adaptive E-Learning System");
	}
	
	public void run() {
		/**
		 * Running Adaptive Engine
		 */
		engine.start(repo);
		engine.learningMash.print();
		// engine.test();
	}

	public static void main(String[] args) {
		//new CentralSystem().run();
	}
	
	public void test() {
		init();
		run();
		engine.test();
	}
	
	public void print() {
		System.out.println("Learning System Bridge Initiated");
	}
	public void printTest() {
		System.out.println("LMS testing");
	}
}


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