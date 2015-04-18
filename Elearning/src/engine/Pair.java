package engine;

public class Pair<L,R> {
	
    private L first;
    private R second;
    
    public Pair(L l, R r){
        this.first = l;
        this.second = r;
    }
    
    public L getFirst() {
    	return first;
    }
    
    public R getSecond() {
    	return second;
    }
    
    public void setFirst(L l) {
    	this.first = l;
    }
    
    public void setSecond(R r) {
    	this.second = r;
    }
}
