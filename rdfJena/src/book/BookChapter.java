package book;

import java.util.HashMap;

public class BookChapter {
	public static final String chapterURI		= "http://lsdis.cs.uga.edu/projects/semdis/opus#Book_Chapter";
	public static final String chapterTitleURI	= "http://www.w3.org/2000/01/rdf-schema#label";
	
	
	String chapterTitle;
	String bookTitle;
	
	int StartPage;
	int EndPage;
	
	int publicationYear;
	
	public HashMap<String, String> tags = new HashMap<>();

	public void print() {
		System.out.println(tags.toString());
	}
}