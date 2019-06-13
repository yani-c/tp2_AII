package duidoku;

public class Casilla {
	
	private Boolean fst;
	private Integer snd;
	
	public Casilla(){
		fst=null;
		snd=null;
	}
	
	public Casilla (Boolean f, Integer s){
		fst=f;
		snd=s;
	}
	
	
	public Boolean getFst() {
		return fst;
	}
	
	public Integer getSnd() {
		return snd;
	}
	
	public void setFst(Boolean f) {
		fst=f;
	}
	
	public void setSnd(Integer s) {
		snd=s;
	}
	
	public boolean equals(Casilla p) {
		return ((p.getFst().compareTo(this.getFst())==0) &&(p.getSnd().compareTo(this.getSnd())==0));
	}
	
	public String toString() {
		String a="("+getFst()+","+getSnd()+")";
		return a;
	}
	
}
