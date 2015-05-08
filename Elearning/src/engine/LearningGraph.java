package engine;

import java.util.ArrayList;
import java.util.HashMap;

import authoring.LearningObject;

public class LearningGraph {

	/**
	 * Each Module has its own graph
	 * 
	 * 
	 * Node - > Learning Object 
	 * Edge is relationship between LO
	 * 
	 * function/formula for creating edges among the Learning Objects
	 * 		Initial Approach
	 * 			-> Just next Chapter
	 * 			-> Similarity in Meta tags
	 * 
	 */
	
	double similarityThreshold = 0.7;
	
	ArrayList<LearningObject> vertexList = new ArrayList<>();
	HashMap <LearningObject, Integer> vertexMap = new HashMap<>();

	ArrayList<ArrayList<LearningObject>> currentList = new ArrayList<>();
	
	HashMap <Integer, Pair<Integer, String> > edgeList = new HashMap<>();
	
	public void addVertex(LearningObject lo) {
		// Add vertex to list
		vertexList.add(lo);
		
		// Add vertex to map
		vertexMap.put(lo, vertexList.size() - 1);
	}
	
	public void generate() {
		int NUM_DIF_STATES = 3;

		currentList.clear();
		for(int i = 0; i < NUM_DIF_STATES; i++) {
			currentList.add(new ArrayList<LearningObject>());
		}
		

		for(LearningObject lo : vertexList) {
			currentList.get(lo.educational.difficulty % NUM_DIF_STATES).add(lo);
		}
	}
	
	public LearningObject getObject(int difficulty) {
		if (currentList.get(difficulty).size() > 0) {
			return currentList.get(difficulty).get(0);
		}
		return null;
	}
	
	public void removeObject(int difficulty) {
		if (currentList.get(difficulty).size() > 0) {
			currentList.get(difficulty).remove(0);
		}
	}
	
	public void addEdge(LearningObject lo1, LearningObject lo2, String value) {
		
		// add edge to edgeList
		Integer indexLO1 = vertexMap.get(lo1);
		Integer indexLO2 = vertexMap.get(lo2);
		Pair<Integer, String> pair = new Pair<>(indexLO2, value);
		
		edgeList.put(indexLO1, pair);
	}
	
	public void generateEdges() {
		
		// edge creation based on vertices in the list. 
		for (Integer i = 0; i < vertexList.size(); i++) {
			for (Integer j = i + 1; j < vertexList.size(); j++) {
				if (vertexList.get(i).similarObject(vertexList.get(j))) {
					// TBD :: Change the value
					addEdge(vertexList.get(i), vertexList.get(j), i.toString() + " " + j.toString());
				} else if (vertexList.get(j).similarObject(vertexList.get(i))) {
					// TBD :: Change to value
					addEdge(vertexList.get(j), vertexList.get(i), j.toString() + " " + i.toString());
				}
			}
		}
	}
	
	public void print() {
		print("\t");
	}
	
	public void print(String indent) {
		for (int i = 0; i < currentList.size(); i++) {
			for (LearningObject lo : currentList.get(i)) {
				lo.print(indent + "\t");
			}
		}
		System.out.println();
	}
}