package authoring;

import java.util.HashMap;

/**
 * @specs
 * IEEE LOM Specification  [ only relevant information to be stored ]
 * 	1. General
 * 		Title 
 * 		Language 
 *		Description
 *		Keyword [Also in Classification]
 *		Date
 *
 *	3. Meta- Metadata
 *		MetaData Schema
 *
 *  4. Technical
 *  	Format 
 *  	Location 
 *  
 *  5. Educational 
 *  	LearningResourceType
 *  	Domain
 *  	Typical Learning Time
 *  	Difficulty
 *  
 *  X. Custom [ Either to be added object or meta tags
 *  	BookName :
 *  	PageNo. :
 *  	Chapter No. :
 *  TBD :: 
 *  Quizzes and Assessments, including: questions, answers
 *  Relationships to Other Courses, including prerequisite courses
 */

public class LearningObject {
	
	public GeneralLOM general = new GeneralLOM();
	public TechnicalLOM technical = new TechnicalLOM();
	public EducationalLOM educational = new EducationalLOM();
	
	public HashMap<String, String> keywords = new HashMap<>();
	
	public String bookName = new String("");
	public Integer startPageNum = new Integer(0); // "FORMAT :: XX-XX"
	public String moduleName = new String("");

	// TBD :: 
	public Integer chapterNum = new Integer(0);
	 
	public void print() {
		print("\t");
	}
	
	public void print(String indent) {
		System.out.println(indent + "learning object");
		
		general.print(indent);
		technical.print(indent);
		educational.print(indent);
		
		System.out.println(indent + "Module : " + moduleName);
		System.out.println(indent + "Book Name : " + bookName);		
	
		System.out.println();
	}
	
	// TBD :: 
	public Boolean similarObject(LearningObject lo2) {
	
		return false;
	}
}
