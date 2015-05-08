package book;

import java.util.HashMap;
import java.util.Map.Entry;

public class Book {
	public static final String bookURI 			= "http://lsdis.cs.uga.edu/projects/semdis/opus#Book";
	public static final String bookTitleURI 	= "http://lsdis.cs.uga.edu/projects/semdis/opus#book_title";
	public static final String bookLanguageURI  = "http://purl.org/dc/elements/1.1/language";
	
	public String title;
	public String language;
	public HashMap<String, String> tags 		= new HashMap<>();
	public HashMap<String, BookChapter> content = new HashMap<>();

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String bookTitle) {
		title = bookTitle;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String Language) {
		language = Language;
	}
	public HashMap<String, BookChapter> getContent() {
		return content;
	}
	
	public void setContent(HashMap<String, BookChapter> bookContent) {
		content = bookContent;
	}
	
	public HashMap<String, String> getTags() {
		return tags;
	}	
	
	public void insert(BookChapter chapter) {
		String chapterTitle = chapter.tags.get(BookChapter.chapterTitleURI);
		chapter.populate();
		getContent().put(chapterTitle, chapter);
	}
	
	public void print() {
		System.out.println("Tags : " + tags);
		for(Entry<String, BookChapter> chapter: getContent().entrySet()) {
            chapter.getValue().print();
    		System.out.println();}
	}
}
