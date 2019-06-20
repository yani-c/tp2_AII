package duidoku;
/**
 * Clase que representa el juego del duidoku
 * @author Yanina Celi , Agustin Borda
 * @version 0.1 15/06/2019
 */
import java.util.LinkedList;
import java.util.List;

public class DuidokuProblem implements AdversarySearchProblem<DuidokuState> {

	private DuidokuState initial;
    private final int minValue;
    private final int maxValue;
	/**
	 * Constructor del problema, construye un estado min con un tablero  null
	 */
	public DuidokuProblem() {
		initial= new DuidokuState(false);
		minValue=Integer.MIN_VALUE;
		maxValue=Integer.MAX_VALUE;
	}
	/**
	 * Construye un problema a partir de un Estado
	 * @pre d != null
	 * @param d El estado dado
	 * @post initial == d
	 */
	public DuidokuProblem(DuidokuState d) {
		initial= d;
		minValue=Integer.MIN_VALUE;
		maxValue=Integer.MAX_VALUE;
	}
	/**
	 * Devuelve el estado inicial del problema(tablero vacio, nodo min)
	 * @pre this != null
	 * @return El estado inicial del juego
	 */
	@Override
	public DuidokuState initialState() {
		Casilla[][] b= new Casilla[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				b[i][j]=new Casilla(true,0);
			}
		}
		boolean m=false;
		DuidokuState d= new DuidokuState(b,m);
		return d;
	}
	/**
	 * Dado un estado, nos devuelve todos los sucesores validos
	 * @pre state != null
	 * @return Una Lista con todos los sucesores validos de state
	 * @post las casillas en las que no se pueda poner nada estan bloqueadas
	 */
	@Override
	public List<DuidokuState> getSuccessors(DuidokuState state) {
		List<DuidokuState> successors = new LinkedList<DuidokuState>();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(state.getBoard()[i][j].getFst()) {
					List<Integer> opciones= state.getOptions(i, j);
					if(!opciones.isEmpty()) {
						for(Integer k: opciones) {
							DuidokuState s= new DuidokuState(state);
							s.getBoard()[i][j]= new Casilla(false,k);
							s.setMax(!state.isMax());
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
	/**
	 * Nos dice si un estado dado es un estado de fin
	 * @pre state != null
	 * @param state El estado que le pasaremos
	 * @return Si el estado es un estado final
	 */
	@Override
	public boolean end(DuidokuState state) {
		return (state.cantCasillasLibres()==0);
	}
	/**
	 * Le asigna un valor numerico a un nodo dependiendo de los favorable que es para el algoritmo
	 * Utiliza 2 funciones, una para el inicio del juego(Que busca bloquear casillas) y otra para la 
	 * parte final(que busca que la cantidad de casillas en blanco sea par, ademas de maximizar los conflictos)
	 * @pre state != null
	 * @param state El estado a evaluar
	 * @return el valor asignado al estado
	 */
	@Override
	public int value(DuidokuState state) {
		int v;
		/*Funcion heuristica para la segunda parte del juego*/
		if(state.cantCasillasLibres()<50) {
			v= ((state.cantCasillasLibres()+1) % 2)+ state.cantConflictos();
		}
		else {
			/*Funcion heuristica para la primera parte del juego*/
			v= state.cantConflictos();
		}
		return v;
	}
	/**
	 * Retorna MinValue;
	 * @return El atributo minValue
	 * @deprecated
	 */
	@Override
	public int minValue() {
		return minValue;
	}
	/**
	 * Retorna MinValue;
	 * @return El atributo minValue
	 * @deprecated
	 */
	@Override
	public int maxValue() {
		return maxValue;
	}

}
