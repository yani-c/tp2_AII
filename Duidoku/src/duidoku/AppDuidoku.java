package duidoku;

public class AppDuidoku {

	public static void main(String[] args) {
		DuidokuProblem p = new DuidokuProblem();
		int depth=10;
	    MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine = new MinMaxAlphaBetaSearchEngine<DuidokuProblem,DuidokuState>(p,depth);
		//capaz necesitemos un for    
	    	int success = engine.computeValue(p.initialState());
		    System.out.println();	    	    
		    System.out.println("*** Result using min max alpha beta ***");
		    String result;
		    if(success==Integer.MAX_VALUE) {
		    	result="Perdiste";
		    }
		    else {
		    	result="Ganaste";
		    }
		    System.out.println(result);
	}
	
}
