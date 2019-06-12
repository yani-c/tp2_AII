package duidoku;

import java.util.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinMaxAlphaBetaSearchEngine <P extends AdversarySearchProblem<S>, S extends AdversarySearchState> extends AdversarySearchEngine<P, S>{


    private S nextMove;
    private int depth; //comento o no?

    /**
     * Constructor por defecto
     */
    public MinMaxAlphaBetaSearchEngine() {
        super();
    }

    /**
     * Constructor que inicializa el motor de búsqueda con el problema p
     * @param p es el problema usado para inicializar
     */
    public MinMaxAlphaBetaSearchEngine(P p) {
        super(p);
    }

    /**
     * 
     * Constructor que inicializa el motor de búsqueda con el problema p y 
     * profundidad máxima maxDepth
     * @param p es el problema usado para inicializar
     * @param maxDepth es la profundidad máxima de búsqueda
     */
    public MinMaxAlphaBetaSearchEngine(P p, int maxDepth) {
        super(p, maxDepth);
    }

    /**
     * Computa un valor para un estado
     * @param state es el estado a evaluar
     * @return devuelve un 'int' con el valor de un estado 'state'
     */
    @Override
    public int computeValue(S state) {
        return minMaxAlphaBeta(state, problem.minValue(), problem.maxValue(), maxDepth);
    }

    /**
     * Implementación recursiva del algoritmo minimax con poda alfa-beta
     * @param state es el estado a evaluar
     * @param alpha es el limite superior de una evaluación
     * @param beta es el limite inferior de una evaluación
     * @param depth es la prfundidad máxima de búsqueda
     * @return devuelve una valoracion para el estado 'state'
     */
    private int minMaxAlphaBeta(S state, int alpha, int beta, int depth) {
        if (this.problem.end(state) || depth == 0) {
            return this.problem.value(state);
        } else {
            List<S> successors = this.problem.getSuccessors(state);
            for (S successor : successors) {
            	if(alpha<beta) {
	            	if (state.isMax()) {
	                    alpha = max(alpha, minMaxAlphaBeta(successor, alpha, beta, depth - 1));
	            	} else {
	            		beta = min(beta, minMaxAlphaBeta(successor, alpha, beta, depth - 1));
	            	}
            	}
            }//endfor
            if (state.isMax()) {
                return alpha;
            } else {
                return beta;
            }//endif
        }//endif
    }

    /**
     * Devuelve el mejor estado sucesor de 'state' para min/max
     * @param state es el estado a evaluar
     * @return devuelve un estado sucesor de 'state'
     */
    @Override
    public S computeSuccessor(S state) {
        boolean isMax = state.isMax();
        List<S> successors = this.problem.getSuccessors(state);
        S result = successors.get(0);
        successors.remove(0);
        int resultValue = this.computeValue(result);
        int successorValue;
        for (S successor : successors) {
            successorValue = this.computeValue(successor);
            if (isMax) {
                if (resultValue < successorValue) {
                    result = successor;
                    resultValue = successorValue;
                }
            } else {
                if (resultValue > successorValue) {
                    result = successor;
                    resultValue = successorValue;
                }
            }
        }//endfor
        nextMove = result;
        return result;
    }

    @Override
    public void report() {
        System.out.println("El siguiente estado es: " + this.nextMove.toString());
    }
}


