package hmm;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CustomHMMRun {

	public static void start() {
		
		int numStates = 3;
		double transisionMatrix[][] = new double[][] {{Math.log(0.3), Math.log(0.6), Math.log(0.1)},
														{Math.log(0.4), Math.log(0.2), Math.log(0.4)},
														{Math.log(0.1), Math.log(0.6), Math.log(0.3)}};
		
		int numOutputs = 3;
		double emisionMatrix[][] = new double[][] {{Math.log(0.5), Math.log(0.3), Math.log(0.2)},
													{Math.log(0.3), Math.log(0.4), Math.log(0.3)},
													{Math.log(0.2), Math.log(0.3), Math.log(0.5)}};
		
		int outputSequence[][] = new int[][] {{0, 2, 1, 1, 2, 2, 1, 0, 0}, {2, 0, 1, 2, 2, 1, 1, 0, 0}};
	
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
		    int errors = 0;
	
		    System.out.println();
		    System.out.print("sequence " + " : ");
		    for (int j  = 0; j < state.length; j++) {
		    	System.out.print(state[j] + " , ");
		    }
		}
	}
	

    // private print formatting stuff
    private static NumberFormat nf = new DecimalFormat("#.000");

    private static String name(String s) {
    	return (s + "    ").substring(0, 4);
    }

    private static String num(double d) {
    	return nf.format(d);
    }
}
