package duidoku;
import graphicInterface.View;
/**
 * Clase que permite jugar al juego "Duidoku"
 * @author Yanina Celi, Agustin Borda
 * @version 0.1 17/06/2019
 */
public class AppDuidoku {
	/**
	 * Metodo main que llama a la intefaz de juego
	 */
	public static void main(String[] args) {
		DuidokuProblem p = new DuidokuProblem();
		int depth=0;
	    MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine = new MinMaxAlphaBetaSearchEngine<DuidokuProblem,DuidokuState>(p,depth);	
	    DuidokuState s= p.initialState();
	    View view = new View(s,engine,p);
	    view.draw();
	}
}
