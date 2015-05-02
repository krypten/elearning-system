package hmm;

public class CustomHMM {

	public int mNumStates;
	public int mNumOutputs;
	
	public String mStates[];
	public String mOutputs[];
	
	public double mTransitionProbability[][];
	public double mEmisionProbability[][];
	
	public double mStartProbability[];
	
	public CustomHMM(int numStates, int numOutputs, double state[][], double output[][], double startProbability[]) 
    {
		this.mNumStates = numStates;
    	this.mNumOutputs = numOutputs;
    	
    	// Initialize transition probability table
    	this.mTransitionProbability = state;
    	
    	// Initialize emision probability table
    	this.mEmisionProbability = output;
    	
    	// Initialize start start state
    	this.mStartProbability = startProbability;
    }
	
	public void setStatesNames(String states[])
	{
		this.mStates = states;
	}
	
	public void setOutputNames(String outputs[])
	{
		this.mOutputs = outputs;
	}
}
