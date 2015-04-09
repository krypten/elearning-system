package hmm;

import hmm.HMM;

/** 
 * Viterbi class, which can be used to compute most likely sequences. 
 */
public class Viterbi {

	/**
	 * Private properties
	 */
	private HMM mHMM;
	private double mBestPathValue[];
	private int mParentState[][];
	private int mNumStates;
	
    /** This is the constructor for this class, which takes as input a
     * given HMM with respect to which most likely sequences will be
     * computed.
     */
    public Viterbi(HMM hmm) 
    {
    	this.mHMM = hmm;
    	this.mNumStates = this.mHMM.getNumStates();
    }

    /** Returns the most likely state sequence for the given
     * <tt>output</tt> sequence, i.e., the state sequence of highest
     * conditional probability given the output sequence, according to
     * the HMM that was provided to the constructor.  The returned
     * state sequence should have the same number of elements as the
     * given output sequence.
     */
    public int[] mostLikelySequence(int output[]) 
    {
    	int[] mostLikelySequence = new int[output.length];
    	this.mBestPathValue = new double[this.mNumStates - 1];
    	this.mParentState = new int[this.mNumStates - 1][output.length];
    	
    	for(int time = 1; time < output.length; time++)
    	{
    		this.updateBestPathValue(output, time);
    	}
    	
    	double maxValue = Double.NEGATIVE_INFINITY;
    	int currentValueIndex = 0;
    	for(int currentState = 0; currentState < this.mHMM.getNumStates() - 1; currentState++)
    	{
    		double currentValue = this.mBestPathValue[currentState];
    		if(currentValue > maxValue)
    		{
    			currentValueIndex = currentState;
    			maxValue = currentValue;
    		}
    	}
    	
    	int optimalStateIndex = currentValueIndex;
    	for(int currentTimeIndex = output.length; currentTimeIndex > 0; currentTimeIndex--)
    	{
    		mostLikelySequence[currentTimeIndex - 1] = optimalStateIndex;
    		optimalStateIndex = this.mParentState[optimalStateIndex][currentTimeIndex - 1];
    	}
    	
    	return mostLikelySequence;
    }
    
    /**
     * Updates the bestPathValue variable and set mParentState matrix
     */

	private void updateBestPathValue(int[] output, int time) 
	{
		int currentOutput = output[time - 1];
		int parrentIndex;
		double[] tempPathValues = new double[this.mNumStates - 1];
		
		if(time == 1)
		{
			for(int currentState = 0; currentState < this.mNumStates - 1; currentState++)
			{
				this.mBestPathValue[currentState] = (this.mHMM.getLogStartProb(currentState)) + 
						(this.mHMM.getLogOutputProb(currentState, currentOutput));
			}
		}
		else
		{
			/**
			 * For each state, computing the value at time t from all the previous states
			 */
			for(int currentState = 0; currentState < this.mNumStates - 1; currentState++)
			{
				double maxValue = Double.NEGATIVE_INFINITY;
				double pathValue;
				parrentIndex = 0;
				
				for(int prevState = 0; prevState < this.mNumStates - 1; prevState++)
				{
					pathValue = this.mBestPathValue[prevState] 
							+ (this.mHMM.getLogTransProb(prevState, currentState)) 
							+ (this.mHMM.getLogOutputProb(currentState, currentOutput));
					
					if(pathValue > maxValue)
					{
						maxValue = pathValue;
						parrentIndex = prevState;
					}
				}
				
				tempPathValues[currentState] = maxValue;
				this.mParentState[currentState][time - 1] = parrentIndex;
			}
			
			for(int currentState = 0; currentState < this.mNumStates - 1; currentState++)
			{
				this.mBestPathValue[currentState] = tempPathValues[currentState];
			}
		}
	}
}
