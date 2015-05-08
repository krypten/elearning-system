package authoring;

public class TechnicalLOM {
	public String format = new String("textual");
	public String location = new String("");
	
	public void print() {
		print("\t");
	}
	
	public void print(String indent) {
		System.out.println(indent + "format : " + format);
		if (location.length() > 0) {
			System.out.println(indent + "location : " + location);
		}
	}
}
