
package authoring;

import java.util.ArrayList;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import book.Book;
import book.BookChapter;
import book.Library;

public class LearningRepository {
	
	public ArrayList<LearningObject> repository = new ArrayList<>();
	
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
			insert(chapter, book.getLanguage());
		}
	}
	
	public void insert(BookChapter chapter, String lang) {
		LearningObject learningObject = new LearningObject();
		
		learningObject.general.title = chapter.chapterTitle;
		learningObject.general.creationDate = new Date(System.currentTimeMillis());
		learningObject.general.publicationYear = new Integer(chapter.publicationYear);
		learningObject.general.language = lang;
		
		
		learningObject.educational.learningResourceType = chapter.learningResourceType;
		learningObject.educational.domain = chapter.domain;
		try {
			learningObject.educational.typicalLearningTime = DatatypeFactory.newInstance().newDuration((chapter.difficulty + 1) * 5000000);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		learningObject.educational.difficulty = chapter.difficulty;
		
		learningObject.keywords = chapter.tags;
		
		learningObject.bookName = chapter.bookTitle;
		learningObject.startPageNum = chapter.startPage;

		// learningObject.chapterNum = new Integer(0);		
		learningObject.moduleName = chapter.moduleName;
		
		insert(learningObject);
	}
	
	public void print() {
		System.out.println("Learning Repository");
		
		String indent = "\t";
		for(LearningObject lo : repository) {
			lo.print(indent);
			System.out.println();
		}
	}
}
