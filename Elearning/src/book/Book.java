package book;

import java.util.HashMap;
import java.util.Map.Entry;

public class Book {
	public static final String bookURI 			= "http://lsdis.cs.uga.edu/projects/semdis/opus#Book";
	public static final String bookTitleURI 	= "http://lsdis.cs.uga.edu/projects/semdis/opus#book_title";
	
	public String title;
	public HashMap<String, String> tags 		= new HashMap<>();
	public HashMap<String, BookChapter> content = new HashMap<>();

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String bookTitle) {
		title = bookTitle;
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
		getContent().put(chapterTitle, chapter);
	}
	
	public void print() {
		System.out.println("Tags : " + tags);
		for(Entry<String, BookChapter> chapter: getContent().entrySet()) {
			System.out.println("\n" + chapter.getKey() + " :: \n\t ");
            chapter.getValue().print();
		}
	}
}
