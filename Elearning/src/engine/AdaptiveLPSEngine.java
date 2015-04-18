package engine;

import java.sql.Time;
import java.util.ArrayList;

import hmm.HMM;
import hmm.Viterbi;
import authoring.LearningRepository;

public class AdaptiveLPSEngine {

	private Viterbi viterbi;
	private LearningRepository learningRepo;
	private LearningForest learningMash = new LearningForest();
	private ArrayList<Integer> outputSequence = new ArrayList<>();

	public void start(LearningRepository repo) {
		this.learningRepo = repo;
		// this.learningMash.populate(learningRepo);
	
		startHMMTest();
	}	
	
	public void initialise(double profileScore) {
		/*
		 * TBD :: Initialize the HMM based on the Profile Score generated from the Test
		 */
		
		int numStates = 3;
		double transisionMatrix[][] = new double[][] {{Math.log(0.6), Math.log(0.3), Math.log(0.1)},
														{Math.log(0.2), Math.log(0.6), Math.log(0.2)},
														{Math.log(0.1), Math.log(0.3), Math.log(0.6)}};
		
		int numOutputs = 3;
		double emisionMatrix[][] = new double[][] {{Math.log(0.6), Math.log(0.4), Math.log(0.0)},
													{Math.log(0.33), Math.log(0.33), Math.log(0.33)},
													{Math.log(0.0), Math.log(0.4), Math.log(0.6)}};
			
		// build HMM from given data
		HMM h = new HMM(numStates, numOutputs, transisionMatrix, emisionMatrix);

		// print HMM
		h.printStartProb();
		h.printOutputProb();
		h.printTransitionProb();
		

		// create Viterbi object for computing most likely sequences
		this.viterbi = new Viterbi(h);
	
		System.out.println();
		
		 // Insert the starting state fir sequence
		this.outputSequence.add(0);

	}
	
	public int nextLearningObject(double objectScore, Time timeElapsed) {
		int currentObservation = observationValue(objectScore, timeElapsed);
		int currentState = updateState(currentObservation);
	
		/*
		 * TBD :: Based on the updated state and get the next Learning Object from the graph.
		 */
		return 0*currentState;
	}
	
	private int updateState(int currentObservation) {
		/*
		 * TBD :: Based on current observation return the next Object
		 */
		
		outputSequence.add(currentObservation);
		
		int state[] = viterbi.mostLikelySequence((Integer[])outputSequence.toArray());
		
	
	    System.out.println();
	    System.out.print("sequence " + " : ");
	    for (int j  = 0; j < state.length; j++) {
	    	System.out.print(state[j] + " , ");
	    }
	    
	    return state[state.length - 1];
	}
	
	private int observationValue(double objectScore, Time timeElapsed) {
		/*
		 *  TBD :: Based on time elapsed and the score return the observation
		 */
		return 0;
	}
	
	private void startHMMTest() {
		int numStates = 3;
		double transisionMatrix[][] = new double[][] {{Math.log(0.6), Math.log(0.3), Math.log(0.1)},
														{Math.log(0.2), Math.log(0.6), Math.log(0.2)},
														{Math.log(0.1), Math.log(0.3), Math.log(0.6)}};

		int numOutputs = 3;
		double emisionMatrix[][] = new double[][] {{Math.log(0.6), Math.log(0.4), Math.log(0.0)},
													{Math.log(0.33), Math.log(0.33), Math.log(0.33)},
													{Math.log(0.0), Math.log(0.4), Math.log(0.6)}};

		Integer outputSequence[][] = new Integer[][] {{0, 2, 1, 1, 2, 2, 1, 0, 0}, {2, 0, 1, 2, 2, 1, 1, 0, 0}};
	
		// build HMM from given data
		HMM h = new HMM(numStates, numOutputs, transisionMatrix, emisionMatrix);

		// print HMM
		h.printStartProb();
		h.printOutputProb();
		h.printTransitionProb();
		

		// create Viterbi object for computing most likely sequences
		Viterbi v = new Viterbi(h);
	
		System.out.println();
	
		// compute and print most likely sequence for each test sequence
		
		for (int i = 0; i < outputSequence.length; i++) {
			int[] state = v.mostLikelySequence(outputSequence[i]);
	
		    System.out.println();
		    System.out.print("sequence " + " : ");
		    for (int j  = 0; j < state.length; j++) {
		    	System.out.print(state[j] + " , ");
		    }
		}
	}
}
