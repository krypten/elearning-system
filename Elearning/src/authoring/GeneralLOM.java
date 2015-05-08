package authoring;

import java.util.Date;

public class GeneralLOM {
	public String title  = new String("");
	public String language  = new String("en");
	public String description = new String("");
	
	public Date creationDate = new Date();
	public Integer publicationYear = new Integer(1990);
	
	public void print() {
		print("\t");
	}
	
	public String getTitle() {
		// System.out.print(title);
		return title;
	}
	
	public void print(String indent) {
		System.out.println(indent + "title : " + title);
		if (description.length() > 0) {
			System.out.println(indent + "description : " + description);
		}
		if (language.length() > 0) {
			System.out.println(indent + "language : " + language);
		}
		System.out.println(indent + "publication year  : " + publicationYear);
	}
}
