package hmm;

import java.util.ArrayList;

public class CustomViterbi {

	public CustomHMM hmm;
	public double bestPathProbability[][];
	
	public CustomViterbi(CustomHMM hmm)
	{
		this.hmm = hmm;
	}
	
	public ArrayList<Integer> start(ArrayList<Integer> obser)
	{
		Integer[] obs = new Integer[obser.size()];
		obs = obser.toArray(obs);
		
		
		int TIME = obs.length;
		this.bestPathProbability = new double[TIME][hmm.mNumStates];
		
		ArrayList<ArrayList<Integer> > path = new ArrayList<>();
		
		for(int i = 0; i < hmm.mNumStates; i++) {
            bestPathProbability[0][i] = hmm.mStartProbability[i] * hmm.mEmisionProbability[i][obs[0]];
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i);
            path.add(temp);
		}
        
        int t;
        int state = obs[0];
        for(t = 1; t < TIME; t++) {
        	ArrayList<ArrayList<Integer> > newPath = new ArrayList<>();

            for(int i = 0; i < hmm.mNumStates; i++) {
	            double prob = - 10;
	            state = -1;
				for(int j = 0; j < hmm.mNumStates; j++) {
					double new_p = bestPathProbability[t-1][j] * hmm.mTransitionProbability[j][i] * hmm.mEmisionProbability[i][obs[t]];
 					
					if (new_p > prob) {
						prob = new_p;
						state = j;
					}
	            }
				
	            this.bestPathProbability[t][i] = prob;
	            newPath.add((ArrayList<Integer>) path.get(state).clone());
	            newPath.get(i).add(i);
		    }
            path = newPath;
        }
        
        printPath(path.get(state));
        return path.get(state);
	}
	
	public void printPath(ArrayList<Integer> path) {
		for (Integer x: path) {
        	System.out.print(hmm.mStates[x] + " ");
        }
        System.out.println();
	}
	
	public void printBestPathProbability() {
		if (bestPathProbability != null) {
			System.out.println("Probability matrix : ");
	        for(int i = 0; i < bestPathProbability.length; i++) {
	            for(int j = 0; j < bestPathProbability[i].length; j++) {
	                System.out.print(bestPathProbability[i][j] + " " );
	            }
	            System.out.println();
	        }
	        System.out.println();
		}
	}
}
