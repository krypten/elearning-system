package engine;

import java.util.HashMap;

import authoring.LearningObject;
import authoring.LearningRepository;

import java.util.ArrayList;

public class LearningForest {

	String databaseCourse = "DBMS";
	HashMap<String, ArrayList<String>> domainModules = new HashMap<>();
	HashMap<String, LearningGraph> graphList = new HashMap<>();

	private void generate() {
		for (LearningGraph graph : graphList.values()) {
			graph.generate();
		}
	}
	
	public void populate(LearningRepository learningRepo) {
		for(LearningObject lo : learningRepo.repository) {
			addLOToModule(lo.moduleName, lo);
			addDomainModule(lo.educational.domain, lo.moduleName);
		}
		this.generate();
	}
	
	public void addLOToModule(String module, LearningObject lo) {
		LearningGraph graph = null;
		if (graphList.containsKey(module)) {
			graph = graphList.get(module);
		} else {
			graph = new LearningGraph();
		}
		graph.addVertex(lo);
		graphList.put(module, graph);
		System.out.println("Object added to " + module + " with title as  " + lo.general.title + " .");
	}
	
	public void addDomainModule(String domain, String module) {
		ArrayList<String> modules = null;
		if (domainModules.containsKey(domain)) {
			modules = domainModules.get(domain);
		} else {
			modules = new ArrayList<String>();
		}
		if (modules.contains(module) == false) {
			modules.add(module);
		}
		domainModules.put(domain, modules);
	}
	
	public LearningObject getLOFromModule(String module, int difficulty) {
		LearningObject lo = null;
		
		for (String key: graphList.keySet()) {
			if (key.contains(module)) {
				lo = graphList.get(key).getObject(difficulty);
				System.out.println(" Returned LO " + lo.general.getTitle() + " from module " + module + ".");
			}
		}
		
		if (lo == null) {
			System.out.println("Could not find the learning object with " + difficulty +" in module " + module + ".");
		}
		
		return lo;
	}
	
	public void removeLOFromModule(String module, int difficulty) {	
		for (String key: graphList.keySet()) {
			if (key.contains(module)) {
				graphList.get(key).removeObject(difficulty);
				System.out.println("Object removed from " + module + " of difficulty " + difficulty + " .\n");
				return ;
			}
		}
		System.out.println("Could not remove object from " + module + " of difficulty " + difficulty + " .\n");
	}
	
	public ArrayList<String> getDomainModules(String domain) {
		System.out.println(" Return " + domain + " domain modules .");
		
		ArrayList<String> modules = null;
		if (domainModules.containsKey(domain)) {
			if (domain.contains(databaseCourse)) {
				modules = getDBModules();
			} else {
				modules = domainModules.get(domain);
			}
		} else {
			System.out.println("Could not find the domain " + domain + " .");
		}
		return modules;
	}
	private ArrayList<String> getDBModules() {
		ArrayList<String> modules = new ArrayList<String>();
		
		String[] array = {"Introduction to DBMS", "Database Design Theory", "Relational Data Modeling",
							"Object based Databases", "Data Storage, Indexing, Query", "Transaction Management",
							"Advance Database Topics"};
		ArrayList<String> dbModules = domainModules.get(databaseCourse);
		for (String module : array) {
			if (dbModules.contains(module)) {
				modules.add(module);
			}
		}
		return modules;
	}
	
	public void print() {
		print("\t");
	}
	
	public void print(String indent) {
		System.out.println("Learning Forest");
		for (String key : graphList.keySet()) {
			System.out.println(indent + "Module Name : "  + key);
			graphList.get(key).print(indent + "\t");
		}
		
		System.out.println("Domain Module list");
		for (String domain : domainModules.keySet()) {
			System.out.println(indent + "Domain : " + domain);
			System.out.println(indent + "\t" + domainModules.get(domain));
		}
	}
}
