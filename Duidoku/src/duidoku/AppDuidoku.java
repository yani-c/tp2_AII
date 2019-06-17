package duidoku;

import java.util.Scanner;
import graphicInterface.View;

public class AppDuidoku {

	public static void main(String[] args) {
		DuidokuProblem p = new DuidokuProblem();
		int depth=0;
	    MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine = new MinMaxAlphaBetaSearchEngine<DuidokuProblem,DuidokuState>(p,depth);	
	    DuidokuState s= p.initialState();
	    while(!p.end(s)) {
	    	if(s.isMax()) {
	    		if(s.cantCasillasLibres()<30) {
	    			engine.setDepth(2);
	    		}
	    		else if(s.cantCasillasLibres()<50){
	    			engine.setDepth(1);
	    		}
	    		else {
	    		}
	    			s = engine.computeSuccessor(s);
	    			s.setMax(false);
	    	}
	    	else {
	    		View view = new View(s);
	    		s =view.draw();
	       		//mostrar tablero
	       		System.out.println(s.toStringBoard());
	       		//dejar que ingrese
	       		Scanner sc= new Scanner(System.in);
	       		System.out.println("Ingrese posicion fila");
	       		int fila= sc.nextInt();
	       		System.out.println("Ingrese posicion columna");
	       		int columna=sc.nextInt();
	        	System.out.println("Puede ingresar: ");
	        	System.out.println(s.getOptions(fila, columna).toString());
	       		System.out.println("Ingresar: ");
	       		int ingreso=sc.nextInt();
	       		s.getBoard()[fila][columna].setFst(false);
	       		s.getBoard()[fila][columna].setSnd(ingreso);
	        	s.setMax(true);
	    	}
	    }
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
