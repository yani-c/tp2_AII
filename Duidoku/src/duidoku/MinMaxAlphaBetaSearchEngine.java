package duidoku;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que implementa el algoritmo MinMax con poda alpha-beta.
 * @author Agustin Borda, Yanina Celi
 * @version 0.1 16/06/2019
 */
public class MinMaxAlphaBetaSearchEngine<P extends AdversarySearchProblem<S>, 
      S extends AdversarySearchState> extends AdversarySearchEngine<P, S> {


  private S nextMove;
  
  /**
   * Constructor por defecto.
  */
  public MinMaxAlphaBetaSearchEngine() {
    super();
  }

  /**
   * Constructor que inicializa el motor de búsqueda con el problema p.
   * @param p : es el problema usado para inicializar : P
  */
  public MinMaxAlphaBetaSearchEngine(P p) {
    super(p);
  }

  /**
   * Constructor que inicializa el motor de búsqueda con el problema p y 
   * profundidad máxima maxDepth.
   * @param p  : es el problema usado para inicializar : P
   * @param maxDepth : es la profundidad máxima de búsqueda : int
  */
  public MinMaxAlphaBetaSearchEngine(P p, int maxDepth) {
    super(p, maxDepth);
  }
    
  public void setDepth(int d) {
    maxDepth = d;
  }

  /**
   * Computa un valor para un estado.
   * @param state : es el estado a evaluar : S
   * @return devuelve un 'int' con el valor de un estado 'state' : int 
  */
  @Override
  public int computeValue(S state) {
    return minMaxAlphaBeta(state, problem.minValue(), problem.maxValue(), maxDepth);
  }

  /**
   * Implementación recursiva del algoritmo minimax con poda alfa-beta.
   * @param state : es el estado a evaluar : DuidokuState
   * @param alpha : es el limite superior de una evaluación : int
   * @param beta : es el limite inferior de una evaluación : int
   * @param depth : es la prfundidad máxima de búsqueda : int
   * @return devuelve una valoracion para el estado 'state' : int
  */
  private int minMaxAlphaBeta(S state, int alpha, int beta, int depth) {
    if (this.problem.end(state) || depth == 0) {
      return this.problem.value(state);
    } else {
      List<S> successors = this.problem.getSuccessors(state);
      for (S successor : successors) {
        if (alpha < beta) {
          if (state.isMax()) {
            alpha = max(alpha, minMaxAlphaBeta(successor, alpha, beta, depth - 1));
          } else {
            beta = min(beta, minMaxAlphaBeta(successor, alpha, beta, depth - 1));
          }
        }
      } //endfor
      if (state.isMax()) {
        return alpha;
      } else {
        return beta;
      } //endif
    } //endif  
  }

  /**
   * Devuelve el mejor estado sucesor de 'state' para min/max, 
   * en caso de haber mas de 1, devuelve uno aleatorio entre los mejores.
   * @pre state no es hoja
   * @param state : es el estado a evaluar : S
   * @return devuelve un estado sucesor de 'state' : S
  */
  @Override
  public S computeSuccessor(S state) {
    boolean isMax = state.isMax();
    List<S> successors = this.problem.getSuccessors(state);
    ArrayList<S> candidates = new ArrayList<S>();//Creamos una lista de candidatos
    candidates.add(successors.get(0));//La inicializamos con el primer elemento de los sucesores
    successors.remove(0);
    int resultValue = this.computeValue(candidates.get(0));//calculamos el valor del primer sucesor
    int successorValue;
    for (S successor : successors) {
      successorValue = this.computeValue(successor);
      /*Si el nodo es max, si tenemos un sucesor con mejor valoracion que los de la lista
       *  de candidatos, creamos una nueva lista de candidatos con el sucesor nuevo*/
      if (isMax) { 
        if (resultValue < successorValue) {
          candidates = new ArrayList<S>();
          candidates.add(successor);
          resultValue = successorValue;
        } else {
          /*Si es de la misma valoracion, agregamos el sucesor a los candidatos*/
          if (resultValue == successorValue) {
            candidates.add(successor);
          }
        }
      } else {
        /*Si el nodo es min, si tenemos un sucesor con peor valoracion que los de la lista
        *  de candidatos, creamos una nueva lista de candidatos con el sucesor nuevo*/
        if (resultValue > successorValue) {
          candidates = new ArrayList<S>();
          candidates.add(successor);
          resultValue = successorValue;
        } else {
          if (resultValue == successorValue) {
            /*Si es de la misma valoracion, agregamos el sucesor a los candidatos*/
            candidates.add(successor);
          }
        }            
      }
    }
    Random r = new Random(candidates.size());
    int index = r.nextInt(candidates.size());
    nextMove = candidates.get(index);/*Elegimos un elemento random de la lista de candidatos*/
    return candidates.get(index);
  }
   
  /**
   * informa quein es el siguiente estado.
  */
  @Override
  public void report() {
    System.out.println("El siguiente estado es: " + this.nextMove.toString());
  }
  
}


