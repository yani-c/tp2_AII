package duidoku;

 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuidokuState implements AdversarySearchState {

	private Casilla[][] board;
	private boolean max;
	private Object ruleApplied;
	
	public DuidokuState() {
		board= new Casilla[9][9];
		ruleApplied=null;
	}
	
	public DuidokuState (boolean m) {
		board= new Casilla[9][9];
		max=m;
		ruleApplied=null;
	}
	
	public DuidokuState(Casilla[][] b, boolean m) {
		board=b;
		max=m;
		ruleApplied=null;
	}
	
	public DuidokuState(Casilla[][] b, boolean m, Object r) {
		board=b;
		max=m;
		ruleApplied=r;
	}
	
	public Casilla[][] getBoard(){
		return board;
	}
	public String toStringBoard(){
		String a="";
		for(int i=0;i<9;i++) {
			a=a+"\n";
			for(int j=0;j<9;j++) {
				a=a+"|"+board[i][j].getSnd()+"|";
			}
		}
		 a=a+"\n";
		 return a;
	}
	

	@Override
	public boolean equals(State other) {
		DuidokuState o = (DuidokuState) other;
		boolean e=true;
		for(int i=0;i<9 && e;i++) {
			for(int j=0;j<9 && e;j++) {
				if(!board[i][j].equals(o.getBoard()[i][j])) {
					e=false;
				}
			}
		}
		return e;
	}

	@Override
	public boolean isMax() {
		return max;
	}

	@Override
	public boolean equals(AdversarySearchState other) {
		//PROBAR						
		return other.equals(other);
	}

	@Override
	public Object ruleApplied() {
		// TODO Auto-generated method stub
		return ruleApplied;
	}

	public int cantCasillasLibres() {
		int cant=0;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(board[i][j].getFst()) {
					cant++;
				}
			}
		}
		return cant;
	}
	

	public int cantConflictos() {
		int cant=0;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if((!board[i][j].getFst())&&board[i][j].getSnd()==-1) {
					cant++;
				}
			}
		}
		return cant;
	}
	
	public List<Integer> getOptions(int i,int j){
		Set<Integer> fila = new HashSet<Integer>();
		Set<Integer> columna = new HashSet<Integer>();
		Set<Integer> seccion = new HashSet<Integer>();
		fila= getOptionsRow(i,j);
		columna= getOptionsColumn(i,j);
		seccion= getOptionsSection(i,j);
		fila.retainAll(columna);
		fila.retainAll(seccion);
		return (List<Integer>) new ArrayList<Integer>(fila);
	}
	
	public Set<Integer> getOptionsRow(int i,int j){
		Set<Integer> estan= new HashSet<Integer>();
		for(int k=0;k<9;k++){
			if(board[k][j].getFst() && board[k][j].getSnd()!=0) {
				estan.add(board[k][j].getSnd());
			}
		}
		Set<Integer> opciones= new HashSet<Integer>();
		for(int m=1;m<10;m++) {
			opciones.add(i);
		}
		//borro las que ya estan en la fila
		opciones.removeAll(estan);
		return opciones;
	}
	
	public Set<Integer> getOptionsColumn(int i,int j){
		Set<Integer> estan= new HashSet<Integer>();
		for(int k=0;k<9;k++){
			if(board[i][k].getFst() && board[j][k].getSnd()!=0) {
				estan.add(board[j][k].getSnd());
			}
		}
		Set<Integer> opciones= new HashSet<Integer>();
		for(int m=1;m<10;m++) {
			opciones.add(i);
		}
		//borro las que ya estan en la fila
		opciones.removeAll(estan);
		return opciones;
	}
	
	public Set<Integer> getOptionsSection(int i,int j){
		int desdeFila=(i/3)*3;
		int desdeColumna=(j/3)*3;
		Set<Integer> estan= new HashSet<Integer>();
		for(int k=desdeFila;k<desdeFila+3;k++) {
			for(int m=desdeColumna;m<desdeColumna+3;m++) {
				if(board[k][m].getFst() && board[k][m].getSnd()!=0) {
					estan.add(board[k][m].getSnd());
				}
			}
		}
		Set<Integer> opciones= new HashSet<Integer>();
		for(int m=1;m<10;m++) {
			opciones.add(i);
		}
		//borro las que ya estan en la fila
		opciones.removeAll(estan);
		return opciones;
	}
	
}
