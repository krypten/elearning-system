package book;

import java.util.HashMap;

public class BookChapter {
	public static final String chapterURI			= "http://lsdis.cs.uga.edu/projects/semdis/opus#Book_Chapter";
	public static final String chapterTitleURI		= "http://www.w3.org/2000/01/rdf-schema#label";
	public static final String publicationYearURI 	= "http://lsdis.cs.uga.edu/projects/semdis/opus#year";
	public static final String pageURI 				= "http://lsdis.cs.uga.edu/projects/semdis/opus#pages";

	public static final String learningResourceTypeURI  = "http://kmr.nada.kth.se/el/ims/schemas/lom-educationaltype";
	public static final String domainURI  = "http://lsdis.cs.uga.edu/projects/semdis/opus#domain";
	public static final String difficultyURI  = "http://kmr.nada.kth.se/el/ims/schemas/lom-educationaldifficulty";
	
	public static final String formatURI  = "http://purl.org/dc/elements/1.1/format";
	public static final String moduleNameURI = "http://lsdis.cs.uga.edu/projects/semdis/opus#module";
	
	public String bookTitle;
	public String chapterTitle;
	
	public String domain;
	public int difficulty;
	public String learningResourceType;
	
	public String format;
	public String moduleName;
	
	public int startPage;
	public int endPage;
	
	public int publicationYear;
	
	public HashMap<String, String> tags = new HashMap<>();

	public void print() {
		System.out.println("\t title : " + chapterTitle);
		System.out.println("\t module name : " + moduleName);
		System.out.println("\t domain : " + domain);
		System.out.println("\t difficulty : " + difficulty);
		System.out.println("\t learning Resource Type : " + learningResourceType);
		System.out.println("\t publication year : " + publicationYear);
		
		System.out.println("\t " + tags.toString());
	}
	
	public void populate() {
		if (tags.containsKey(Book.bookTitleURI)) {
			bookTitle = tags.get(Book.bookTitleURI);
			tags.remove(Book.bookTitleURI);
		}
		
		if (tags.containsKey(BookChapter.chapterTitleURI)) {
			chapterTitle = tags.get(BookChapter.chapterTitleURI);
			tags.remove(BookChapter.chapterTitleURI);
		}
		
		if (tags.containsKey(BookChapter.learningResourceTypeURI)) {
			learningResourceType = tags.get(BookChapter.learningResourceTypeURI);
			tags.remove(BookChapter.learningResourceTypeURI);
		}
		
		if (tags.containsKey(BookChapter.domainURI)) {
			domain = tags.get(BookChapter.domainURI);
			tags.remove(BookChapter.domainURI);
		}
		
		if (tags.containsKey(BookChapter.difficultyURI)) {
			difficulty = (int) Integer.parseInt(tags.get(BookChapter.difficultyURI));
			tags.remove(BookChapter.difficultyURI);
		}
		
		if (tags.containsKey(BookChapter.formatURI)) {
			format = tags.get(BookChapter.formatURI);
			tags.remove(BookChapter.formatURI);
		}
		
		if (tags.containsKey(BookChapter.moduleNameURI)) {
			moduleName = tags.get(BookChapter.moduleNameURI);
			tags.remove(BookChapter.moduleNameURI);
		}
		
		if (tags.containsKey(BookChapter.publicationYearURI)) {
			publicationYear = getYear(tags.get(BookChapter.publicationYearURI));
			tags.remove(BookChapter.publicationYearURI);
		}
		
		if (tags.containsKey(BookChapter.pageURI)) {
			startPage = getPage(tags.get(BookChapter.pageURI), true);
			endPage = getPage(tags.get(BookChapter.pageURI), false);
			tags.remove(BookChapter.pageURI);
		}

	}
	
	private int getYear(String publicationYear) {
		publicationYear = publicationYear.substring(0, publicationYear.indexOf("^^"));
		return (int) Integer.parseInt(publicationYear);
	}
	
	private int getPage(String pages, Boolean findStartPage) {
		String page = "";
		if (findStartPage == true) {
			page = pages.substring(0, pages.indexOf("-"));
		} else {
			page = pages.substring(pages.indexOf("-") + 1);
		}
		return (int) Integer.parseInt(page);
	}
}