package lightUp;

import org.jgap.*;

public class makeFitnessFunction extends FitnessFunction{

	//private static final long serialVersionUID = a;
	
	private final Casilla[][] board;

    public makeFitnessFunction(Casilla[][] board){
        this.board=board;
    }

	@Override
	protected double evaluate(IChromosome c) {
		int cantIluminadas= getCantIluminadas(c);
		int cantConflictos= getCantConflictos(c);
		return ((cantIluminadas)/(cantConflictos + 1));
	}
	
	public int getCantIluminadas(IChromosome c) {
		Boolean[][] iluminadas= new Boolean[7][7];
		Casilla[][] aux= completeBoard(c);
		inicializarTablero(iluminadas);
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				//si es una casilla blanca y tiene foco
				if(aux[i][j].getFst() && aux[i][j].getSnd()>0) {
					//iluminamos tablero
					iluminarTablero(aux,iluminadas,i,j);
				}
			}
		}
		int cant=0;
		for(int k=0;k<7;k++) {
			for(int m=0;m<7;m++) {
				if(iluminadas[k][m]) {
					cant++;
				}
			}
		}
		return cant;
	}
	
	private void inicializarTablero(Boolean[][] iluminadas) {
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				iluminadas[i][j]=false;
			}
		}
	}
	
	
	public void iluminarTablero(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
		
		iluminarArriba(board,iluminadas,i,j);
		iluminarAbajo(board,iluminadas,i,j);
		iluminarDerecha(board,iluminadas,i,j);
		iluminarIzquierda(board,iluminadas,i,j);
	}
	
	public void iluminarArriba(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
		while(i>=0 && board[i][j].getFst()) {
			iluminadas[i][j]=true;
			i--;
		}
	}
	
	public void iluminarAbajo(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
		while(i<7 && board[i][j].getFst()) {
			iluminadas[i][j]=true;
			i++;
		}
	}
	
	public void iluminarDerecha(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
		while(j<7 && board[i][j].getFst()) {
			iluminadas[i][j]=true;
			j++;
		}
	}
	
	public void iluminarIzquierda(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
		while(j>=0 && board[i][j].getFst()) {
			iluminadas[i][j]=true;
			j++;
		}
	}
	
	
	public Casilla[][] completeBoard(IChromosome c){
		Casilla[][] boardAux= new Casilla[7][7];
		int k = 0;
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				if(board[i][j].getFst()) {
					boardAux[i][j]= (Casilla) c.getGene(k);
				}
				else {
					boardAux[i][j]=board[i][j];
				}
				k++;
			}
		}
		return boardAux;
		
		}
	
	public int getCantConflictos(IChromosome c) {
		int cant= conflictosLugar(c);
		cant=cant+conflictosCasillasNegras(c);
		return cant;
	}
	
	public int conflictosLugar(IChromosome c) {
		int conflictos=0;
		//miro las filas
		for(int i=0;i<7;i++) {
			boolean hayFoco=false;
			for(int j=0;j<7;j++) {
				if(board[i][j].getFst() && !hayFoco) {
					hayFoco=true;
				}
				else if(!board[i][j].getFst() && hayFoco) {
					hayFoco=false;
				}
				else if(board[i][j].getFst() && hayFoco) {
					conflictos++;
				}
			}
		}
		//miro las columnas
		for(int i=0;i<7;i++) {
			boolean hayFoco=false;
			for(int j=0;j<7;j++) {
				if(board[j][i].getFst() && !hayFoco) {
					hayFoco=true;
				}
				else if(!board[j][i].getFst() && hayFoco) {
					hayFoco=false;
				}
				else if(board[j][i].getFst() && hayFoco) {
					conflictos++;
				}
			}
		}
		return conflictos;
	}
	
	public int conflictosCasillasNegras(IChromosome c) {
		int conflictos=0;
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				if(!board[i][j].getFst() && board[i][j].getSnd()>0) {
					int focos=0;
					//miro arriba de la casilla, si es que se puede
					if(i-1>=0 && board[i-1][j].getFst() && board[i-1][j].getSnd()==1) {
						//si puedo moverme para arriba, y la de arriba es una casilla blanca y si tiene  foco
						focos++;
					}
					//miro abajo de la casilla, si es que se puede
					if(i+1<7 && board[i+1][j].getFst() && board[i+1][j].getSnd()==1) {
						//
						focos++;
					}
					//miro a la derecha de la casilla, si es que se puede
					if(j+1<7 && board[i][j+1].getFst() && board[i][j+1].getSnd()==1) {
						//
						focos++;
					}
					//miro a la izquierda de la casilla, si es que se puede
					if(j-1>=0 && board[i][j-1].getFst() && board[i][j-1].getSnd()==1) {
						//
						focos++;
					}
					if(focos!=board[i][j].getSnd()) {
						conflictos++;
					}
				}
			}
		}
		return conflictos;
	}
	
	
}
