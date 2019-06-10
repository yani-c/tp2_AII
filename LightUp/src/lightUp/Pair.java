package lightUp;

public class Pair<V extends Comparable,U extends Comparable> {
	
	private V fst;
	private U snd;
	
	public Pair(){
		fst=null;
		snd=null;
	}
	
	public Pair (V f, U s){
		fst=f;
		snd=s;
	}
	
	public V getFst() {
		return fst;
	}
	
	public U getSnd() {
		return snd;
	}
	
	public void setFst(V f) {
		fst=f;
	}
	
	public void setSnd(U s) {
		snd=s;
	}
	
	public boolean equals(Pair<V,U> p) {
		return ((p.getFst().compareTo(this.getFst())==0) &&(p.getSnd().compareTo(this.getSnd())==0));
	}
	
}
