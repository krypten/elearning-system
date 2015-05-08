package engine;

import java.util.ArrayList;

import php.java.bridge.PhpProcedure;
import hmm.CustomHMM;
import hmm.CustomViterbi;
import authoring.LearningRepository;

public class AdaptiveLPSEngine {

	private CustomViterbi viterbi;
	private LearningRepository learningRepo;
	public LearningForest learningMash = new LearningForest();
	
	private int currentPosition;
	private int numModules;
        
	private ArrayList<Integer> observationSequence;

	public void start() {
		if (this.learningRepo != null) {
			this.learningMash.populate(this.learningRepo);
		} else {
			start(null);
		}
	}
	
	public void start(LearningRepository repo) {
		this.learningRepo = repo;
		if (this.learningRepo != null) {
			this.learningMash.populate(this.learningRepo);
		}
		
		init();
	}

	
	public void init() {
        double start_probabilities[] = {0.6, 0.3, 0.1};

		double transition_probabilities[][] = {{0.6, 0.3, 0.1},
		                                       {0.25, 0.5, 0.25},
		                                       {0.1, 0.3, 0.6}};

		double emission_probabilities[][] = {{0.6, 0.3, 0.1},
		                                     {0.33, 0.34, 0.33},
		                                     {0.1, 0.3, 0.6}};
		

		String states[] = {"Beginner", "Intermediate", "Advanced"};
        String outputs[] = {"Deteriorate", "Static", "Improvement"};

        // build HMM from given data
        CustomHMM h = new CustomHMM(states.length, outputs.length, transition_probabilities, emission_probabilities, start_probabilities);
        h.setStatesNames(states);
        h.setOutputNames(outputs);

        this.viterbi = new CustomViterbi(h);
    }
	
	public void init(int observation) {
		if (observation == 0) {
			this.viterbi.hmm.mStartProbability = new double[]{0.6, 0.3, 0.1};
		} else if (observation == 1) {
			this.viterbi.hmm.mStartProbability = new double[]{0.3, 0.6, 0.1};;
		} else {
			this.viterbi.hmm.mStartProbability = new double[]{0.1, 0.3, 0.6};;
		}
	}

	public void clearObservations() {
		if (observationSequence != null) {
			observationSequence.clear();
		}
		currentPosition = 0;
    }
    
    public void initModule(int num_Modules) {
        numModules = num_Modules;
        observationSequence = arrayListInit(numModules);
    }
    
    public int addObservation(int currentObservation) {
        observationSequence.set(currentPosition, currentObservation);
        ArrayList<Integer>state = this.viterbi.start(this.observationSequence);
        printSequence(state);
        currentPosition++;
        return state.get(currentPosition - 1);
    }
    
	public void test() {
		// startCustomHMMTest();
		ArrayList<Integer> states = new ArrayList<>();
		Integer obs[] = new Integer[] {2, 2, 1, 1, 2};
		
		clearObservations();
		init();
		initModule(obs.length);
		
		for (Integer i : obs) {
			states.add( addObservation(i) );
		}
		
		System.out.println(states);
	}
	
	public ArrayList<Integer> getStates(ArrayList<Integer> observations) {
		return this.viterbi.start(observations);
	}

	public ArrayList<Integer> getObsStates() {
        System.out.println("Current observation sequence: " + observationSequence);
		return observationSequence;
	}
	
	public int getObsState() {
		return observationSequence.get(currentPosition - 1);
	}
	
	public int[] getStates(int[] observations) {
		return convertToArray(getStates(convertToList(observations)));
	}
		
	public int getState(int[] observations, int index) {
		int[] states = getStates(observations);
		if (index < states.length) { 
			return states[index];
		}
		return 0;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	public void printSequence(ArrayList<Integer> state) {
	    System.out.print("Sequence " + " : ");
	    for (int j  = 0; j < state.size(); j++) {
	    	System.out.print(state.get(j) + (j == state.size()-1 ? "\n\n" : " , "));
	    }
	}
	
	private ArrayList<Integer> arrayListInit(int num){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i < num; i ++){
            temp.add(0);
        }
        return temp;
    }
	
	private int updateState(int currentObservation) {
		/*
		 * Based on current observation, returns the state
		 */
		
		observationSequence.add(currentObservation);
		
		ArrayList<Integer>state = this.viterbi.start(this.observationSequence);
		printSequence(state);
		
	    return state.get(state.size() - 1);
	}
	
	private int[] convertToArray(ArrayList<Integer> list) {
		int[] iArray = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			iArray[i] = (int) list.get(i);
		}
		return iArray;
	}
	
	private ArrayList<Integer>  convertToList(int[] array) {
		ArrayList<Integer> iList = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			iList.add((Integer) array[i]);
		}
		return iList;
	}
}

/*
	private void startCustomHMMTest() {
	
		double start_probabilities[] = {0.6, 0.3, 0.1};
		
		double transition_probabilities[][] = {{0.6, 0.3, 0.1},
		                                       {0.25, 0.5, 0.25},
		                                       {0.1, 0.3, 0.6}};
		
		double emission_probabilities[][] = {{0.6, 0.3, 0.1},
		                                     {0.33, 0.34, 0.33},
		                                     {0.1, 0.3, 0.6}};
		
		
		String states[] = {"Beginner", "Intermediate", "Advanced"};
		String outputs[] = {"Deteriorate", "Static", "Improvement"};
		
		CustomHMM h = new CustomHMM(3, 3, transition_probabilities, emission_probabilities, start_probabilities);
		h.setStatesNames(states);
		h.setOutputNames(outputs);
		
		this.viterbi = new CustomViterbi(h);
	}
*/