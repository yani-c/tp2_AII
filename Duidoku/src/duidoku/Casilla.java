package duidoku;

/**
 * Clase auxiliar para representar el tablero
 */
public class Casilla {
	
	/**
	 * si "fst" es true, representa una casilla blanca, y si es false, una casilla negra
	 * si la casilla es blanca, snd representa si la casilla tiene foco (si es 1) o no (si es 0) 
	 */
	private Boolean fst;
	private Integer snd;
	
	
	//constructores 
	
	/**
	 * constructor por defecto
	 */
	public Casilla(){
		fst=null;
		snd=null;
	}
	
	/**
	 * constructor a partir de valores
	 * @param f : valor a asignar en fst : Boolean
	 * @param s : valor a asignar en snd : Integer
	 */
	public Casilla (Boolean f, Integer s){
		fst=f;
		snd=s;
	}
	
	//gets
	
	/**
	 * @return valor del atributo fst : Boolean
	 */
	public Boolean getFst() {
		return fst;
	}
	
	/**
	 * @return valor del atributo snd : Integer
	 */
	public Integer getSnd() {
		return snd;
	}
	
	//sets
	
	/**
	 * cambia el valor de fst por f
	 * @param f : valor a asignarle a fst : Boolean
	 */
	public void setFst(Boolean f) {
		fst=f;
	}
	
	/**
	 * cambia el valor de snd por s
	 * @param s : valor a asignarle a snd : Integer
	 */
	public void setSnd(Integer s) {
		snd=s;
	}
	
	/**
	 *Compara dos casillas 
	 *@param p : casilla con que la se va a comparar : Casilla
	 *@return valor de verdad de comparar this con p : boolean
	 */
	public boolean equals(Casilla p) {
		return ((p.getFst().compareTo(this.getFst())==0) &&(p.getSnd().compareTo(this.getSnd())==0));
	}
	
	/**
	 * @return casilla en forma de String, con fst y snd : String
	 */
	public String toString() {
		String a="("+getFst()+","+getSnd()+")";
		return a;
	}
	
	
}
