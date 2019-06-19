package duidoku;


import graphicInterface.View;

public class AppDuidoku {

	public static void main(String[] args) {
		DuidokuProblem p = new DuidokuProblem();
		int depth=0;
	    MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine = new MinMaxAlphaBetaSearchEngine<DuidokuProblem,DuidokuState>(p,depth);	
	    DuidokuState s= p.initialState();
	    View view = new View(s,engine);
	    view.draw();
	    String result;
	    if(p.value(s)==Integer.MAX_VALUE) {
	    	result="Perdiste";
	    }
	    else {
	    	result="Ganaste";
	    }
	    	System.out.println(result);
	}
	
}
