package book;

import java.util.HashMap;

public class BookChapter {
	public static final String chapterURI		= "http://lsdis.cs.uga.edu/projects/semdis/opus#Book_Chapter";
	public static final String chapterTitleURI	= "http://www.w3.org/2000/01/rdf-schema#label";
	
	
	public String chapterTitle;
	public String bookTitle;
	
	public int StartPage;
	public int EndPage;
	
	public int publicationYear;
	
	public HashMap<String, String> tags = new HashMap<>();

	public void print() {
		System.out.println(tags.toString());
	}
}