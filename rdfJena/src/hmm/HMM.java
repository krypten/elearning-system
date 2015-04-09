package hmm;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HMM {

	/**
	 * Private properties
	 */
	private int mNumStates;
	private int mNumOutputs;
	private double mTransitionProbability[][];
	private double mEmisionProbability[][];
	private int mDummyStateIndex;
	
    /** Constructs an HMM from the given data. 
     *  The HMM will have <tt>numStates</tt> possible states and
     *  <tt>numOutputs</tt> possible outputs.
     *  The HMM is then built from the given set of state and output sequences.  In particular,
     * <tt>state[i][j]</tt> is the <tt>j</tt>-th element of the
     * <tt>i</tt>-th state sequence, and similarly for <tt>output[i][j]</tt>.
     */
 
	public HMM(int numStates, int numOutputs, int state[][], int output[][]) 
    {
       	this.mNumStates = numStates + 1; //numStates + dummy state
    	this.mNumOutputs = numOutputs;
    	this.mDummyStateIndex = numStates - 1; //index for the dummy state
    	
    	//Initialize transition probability table
    	this.mTransitionProbability = new double[this.mNumStates][this.mNumStates];
    	
    	//Initialize emision probability table
    	this.mEmisionProbability = new double[this.mNumStates][this.mNumOutputs];
    	
    	//Build probabilities
    	this.buildHMM(state, output);
    }
	
	public HMM(int numStates, int numOutputs, double state[][], double output[][]) 
    {
		this.mNumStates = numStates + 1; //numStates + dummy state
    	this.mNumOutputs = numOutputs;
    	this.mDummyStateIndex = numStates - 1; //index for the dummy state

    	//Initialize transition probability table
    	this.mTransitionProbability = state;
    	
    	//Initialize emision probability table
    	this.mEmisionProbability = output;
    }
	
    /** Returns the number of states in this HMM. 
     *	Number of Hidden states
     */
    public int getNumStates() 
    {
    	return this.mNumStates;
    }

    /** Returns the number of output symbols for this HMM. 
     *	Number of Observable states
     */
    public int getNumOutputs() 
    {
    	return this.mNumOutputs;
    }

    /** Returns the log probability assigned by this HMM to a
     * transition from the dummy start state to the given
     * <tt>state</tt>.
     */
    public double getLogStartProb(int state) 
    {
    	return this.mTransitionProbability[this.mDummyStateIndex][state];
    }

    /** Returns the log probability assigned by this HMM to a
     * transition from the dummy start state to the given
     * <tt>state</tt>.
     */
    public double getStartProb(int state) 
    {
    	return Math.exp(this.mTransitionProbability[this.mDummyStateIndex][state]);
    }

    /** Returns the log probability assigned by this HMM to a
     * transition from <tt>fromState</tt> to <tt>toState</tt>.
     */
    public double getLogTransProb(int fromState, int toState) 
    {
    	return this.mTransitionProbability[fromState][toState];
    }

    /** Returns the log probability assigned by this HMM to a
     * transition from <tt>fromState</tt> to <tt>toState</tt>.
     */
    public double getTransProb(int fromState, int toState) 
    {
    	return Math.exp(this.mTransitionProbability[fromState][toState]);
    }

    /** Returns the log probability of <tt>state</tt> emitting
     * <tt>output</tt>.
     */
    public double getLogOutputProb(int state, int output) 
    {
    	return this.mEmisionProbability[state][output];
    }
    
    /** Returns the probability of <tt>state</tt> emitting
     * <tt>output</tt>.
     */
    public double getOutputProb(int state, int output) 
    {
    	return Math.exp(this.mEmisionProbability[state][output]);
    }
    
    /**
     * Printing Utilities
     */
    
    public void printStartProb()
    {
	    System.out.println("Start probabilities:");
	    for (int s = 0; s < getNumStates() - 1; s++)
		System.out.println("States " + ": " + num(getStartProb(s)));
    }
    
	public void printOutputProb()
	{
		System.out.println();
		System.out.println("Output probabilities: ");
	
		for (int s = 0; s < getNumStates() - 1; s++) {
			System.out.print("States " + ":" );
		    for (int o = 0; o < getNumOutputs(); o++) {
			System.out.print(" "
				   + num(getOutputProb(s,o)));
		    }
		    System.out.println();
		}
	}
	
	public void printTransitionProb()
	{
		System.out.println();
	    System.out.println("Transition probabilities: ");

	    for (int s = 0; s < getNumStates() - 1; s++) {
			System.out.print("States " + ":" );
			for (int u = 0; u < getNumStates() - 1; u++) {
			    System.out.print(" " + num(getTransProb(s,u)));
			}
			System.out.println();
		}
	}
	
    private static NumberFormat nf = new DecimalFormat("#.000");

    private static String num(double d) {
    	return nf.format(d);
    }

    /**
     * Fill the matrix mTransitionProbability, mEmisionProbability
     *  with computed probabilities.
     */
    private void buildHMM(int state[][], int output[][])
    {
    	int currentState;
    	
    	int[][] transitionCount = new int[this.mNumStates][this.mNumStates];
    	int[][] outputProbabilitiesCount = new int[this.mNumStates][this.mNumOutputs];
    	int[] outputCount = new int[this.mNumStates];
    	int[] transitionsState = new int[this.mNumStates];
    	
    	for(int row = 0; row < state.length; row++)
    	{
    		int lastState = this.mDummyStateIndex;
    		for(int column = 0; column < state[row].length; column++)
    		{
    			currentState = state[row][column];
    			transitionCount[lastState][currentState] += 1;
    			outputProbabilitiesCount[currentState][output[row][column]] += 1;
    			transitionsState[lastState] += 1;
    			outputCount[currentState] += 1;
    			lastState = currentState;
    		}
    	}
    	
    	this.updateTransitionProbability(transitionCount, transitionsState);
    	this.updateEmisionProbabilit(outputProbabilitiesCount, outputCount);
    }
    
    private void updateTransitionProbability(int [][] transitionCount, int[] transitionState)
    {
    	for(int row = 0; row < this.mNumStates; row ++)
    	{
    		for(int column = 0; column < this.mNumStates - 1; column ++)
    		{
    			this.mTransitionProbability[row][column] = 
    					Math.log((((double)transitionCount[row][column] + 1) / (transitionState[row] + this.mNumStates - 1)));
    		}
    	}
    }

    private void updateEmisionProbabilit(int[][] outputProbabilitesCount, int[] outputCount)
    {
    	for(int row = 0; row < this.mNumStates - 1; row ++)
    	{
    		for(int column = 0; column < this.mNumOutputs; column ++)
    		{
    			this.mEmisionProbability[row][column] = 
    					Math.log((((double)outputProbabilitesCount[row][column] + 1) / (outputCount[row] + this.mNumOutputs)));
    		}
    	}
    }
}
