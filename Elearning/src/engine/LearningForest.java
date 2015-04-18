package engine;

import java.util.HashMap;

import authoring.LearningObject;
import authoring.LearningRepository;

public class LearningForest {

	HashMap<String, LearningGraph> graphList = new HashMap<>();

	private void generate() {
		for (LearningGraph graph : graphList.values()) {
			graph.generate();
		}
	}
	
	public void addLearningObjectToModule(String module, LearningObject lo) {
		if (graphList.containsKey(module)) {
			LearningGraph graph = graphList.get(module);
			graph.addVertex(lo);
		}
	}
	
	public void populate(LearningRepository learningRepo) {
		for(LearningObject lo : learningRepo.repository) {
			addLearningObjectToModule(lo.moduleName, lo);
		}
		this.generate();
	}
}
