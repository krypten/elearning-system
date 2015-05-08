package authoring;

import javax.xml.datatype.Duration;

public class EducationalLOM {
	public String learningResourceType = new String("textual");
	public String domain = new String("db");
	public Duration typicalLearningTime = null;
	public Integer difficulty = new Integer(0);
	
	public void print() {
		print("\t");
	}
	
	public void print(String indent) {
		System.out.println(indent + "Resource Type : " + learningResourceType);
		System.out.println(indent + "Difficulty : " + difficulty);
		System.out.println(indent + "Learning Time : " + typicalLearningTime.getHours() + ":" + typicalLearningTime.getMinutes());
		System.out.println(indent + "domain  : " + domain);
	}
}
