package duidoku;

import java.util.LinkedList;
import java.util.List;

public class DuidokuProblem implements AdversarySearchProblem<DuidokuState> {

	private DuidokuState initial;
    private final int minValue;
    private final int maxValue;
	
	public DuidokuProblem() {
		initial= new DuidokuState();
		minValue=Integer.MIN_VALUE;
		maxValue=Integer.MAX_VALUE;
	}
	
	public DuidokuProblem(DuidokuState d) {
		initial= d;
		minValue=Integer.MIN_VALUE;
		maxValue=Integer.MAX_VALUE;
	}
	
	@Override
	public DuidokuState initialState() {
		Casilla[][] b= new Casilla[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				b[i][j]=new Casilla(true,0);
			}
		}
		boolean m=true;
		DuidokuState d= new DuidokuState(b,m);
		return d;
	}

	@Override
	public List<DuidokuState> getSuccessors(DuidokuState state) {
		List<DuidokuState> successors = new LinkedList();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(state.getBoard()[i][j].getFst()) {
					List<Integer> opciones= state.getOptions(i, j);
					if(!opciones.isEmpty()) {
						for(Integer k: opciones) {
							DuidokuState s= state;
							s.getBoard()[i][j]= new Casilla(false,k);
							successors.add(s);
						}
					}
					else {
						//compueba si hay conflictos, y los señala
						state.getBoard()[i][j].setFst(false);
						state.getBoard()[i][j].setSnd(-1);
					}
				}
			}
		}
		return successors;
	}

	@Override
	public boolean end(DuidokuState state) {
		return (state.cantCasillasLibres()==0);
	}

	@Override
	public int value(DuidokuState state) {
		int v;
		if(end(state)) {
			//si ultimo en jugar fue un max
			if(!state.isMax()) {//PROBAR
				v=maxValue();
			}
			//si el ultimo en jugar fue un min
			else{
				v=minValue();
			}
		}
		else {
			//si ya voy avanzado en el juego
			if(state.cantCasillasLibres()>40) {
				v= ((state.cantCasillasLibres()+1) % 2)+ state.cantConflictos();
			}
			else {
				v= state.cantConflictos();
			}
		}
		return v;
	}
	
	@Override
	public int minValue() {
		return minValue;
	}

	@Override
	public int maxValue() {
		return maxValue;
	}

}
