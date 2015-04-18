
package authoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import book.Book;
import book.BookChapter;
import book.Library;

public class LearningRepository {
	
	public ArrayList<LearningObject> repository;
	
	public void insert(LearningObject lo) {
		repository.add(lo);
	}
	
	public void insert(Library lib) {
		HashMap<String, Book> store = lib.getStore();
		
		for (Book book: store.values()) {
			insert(book);
		}
		
	}
	
	/*
	 * TBD ::  Chapter number
	 */
	public void insert(Book book) {	
		HashMap<String, BookChapter> content = book.getContent();
		for (BookChapter chapter: content.values()) {
			insert(chapter);
		}
	}
	
	public void insert(BookChapter chapter) {
		LearningObject learningObject = new LearningObject();
		
		learningObject.general.title = chapter.chapterTitle;
		learningObject.general.creationDate = new Date(System.currentTimeMillis());
		learningObject.general.publicationYear = new Integer(chapter.publicationYear);
		
		// learningObject.educational.learningResourceType = new String("");
		// learningObject.educational.domain = new String("");
		// learningObject.educational.typicalLearningTime = new Time(0);
		// learningObject.educational.Difficulty = new Integer(0);
		
		// learningObject.keywords = chapter.tags;
		
		learningObject.bookName = chapter.bookTitle;
		learningObject.startPageNum = chapter.StartPage;

		// learningObject.chapterNum = new Integer(0);		
		// learningObject.moduleName = new String("");
		
		insert(learningObject);
	}
}
