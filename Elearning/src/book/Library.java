package book;

import java.util.HashMap;
import java.util.Map.Entry;

public class Library {
	public HashMap<String, Book> store = new HashMap<>();
	
	public HashMap<String, Book> getStore() {
		return store;
	}
	
	public Book getBookByTitle(String title) {
		if (getStore().containsKey(title)) { 
			return getStore().get(title);
		}
		return null;
	}
	
	public void insert(BookChapter chapter) {
		if (chapter.tags.containsKey(Book.bookTitleURI) ) {
			String bookTitle = chapter.tags.get(Book.bookTitleURI);
			
			Book book = getBookByTitle(bookTitle);
			if (book == null) {
				(book = new Book()).setTitle(bookTitle);
			}
			book.insert(chapter);
			insert(book);
		}
	}
	
	public void insert(Book book) {
		getStore().put(book.getTitle(), book);
	}
	
	public void print() {
		
		for(Entry<String, Book> book : getStore().entrySet()){
            System.out.println("\n" + book.getKey() + " :: \n\t ");
            book.getValue().print();
		}
		System.out.println(getStore());
	}
	
	public static void insertBookChapterInLibrary(BookChapter chapter, Library lib) {
		if (chapter.tags.containsKey(Book.bookTitleURI) ) {
			String bookTitle = chapter.tags.get(Book.bookTitleURI);
			String chapterTitle = chapter.tags.get(BookChapter.chapterTitleURI);
			
			Book book;
			if (lib.getStore().containsKey(bookTitle)) {
				book = lib.getStore().get(bookTitle);
			} else {
				book = new Book();
			}
			book.content.put(chapterTitle, chapter);
			lib.getStore().put(bookTitle, book);
		}
	}
}
