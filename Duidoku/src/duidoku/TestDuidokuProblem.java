package duidoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDuidokuProblem {

  /**
   * atributo usado para hacer test.
  */
  private Casilla[][] board = { {new Casilla(true,0),new Casilla(false,7),new Casilla(false,6),
      new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,1),
      new Casilla(false,9),new Casilla(false,2)},
    {new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),
      new Casilla(false,1),new Casilla(false,9),new Casilla(false,7),new Casilla(false,6),
      new Casilla(false,8)},
    {new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),
      new Casilla(false,7),new Casilla(false,6),new Casilla(false,4),new Casilla(false,3),
      new Casilla(false,5)},
    {new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),
      new Casilla(false,6),new Casilla(false,5),new Casilla(false,3),new Casilla(false,2),
      new Casilla(false,4)},
    {new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),
      new Casilla(false,9),new Casilla(false,8),new Casilla(false,6),new Casilla(false,5),
      new Casilla(false,7)},
    {new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),
      new Casilla(false,3),new Casilla(false,2),new Casilla(false,9),new Casilla(false,8),
      new Casilla(false,1)},
    {new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),
      new Casilla(false,8),new Casilla(false,7),new Casilla(false,5),new Casilla(false,4),
      new Casilla(false,6)},
    {new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),
      new Casilla(false,2),new Casilla(false,1),new Casilla(false,8),new Casilla(false,7),
      new Casilla(false,9)},
    {new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),
      new Casilla(false,5),new Casilla(false,4),new Casilla(false,2),new Casilla(false,1),
      new Casilla(false,3)}
  };
  
  /**
    * Testea el metodo getSuccessors.
  */
  @Test
  void testGetSuccessors() {
    DuidokuState stateMax = new DuidokuState(board,true);
    DuidokuState stateAux = new DuidokuState(stateMax);
    stateAux.setMax(false);
    stateMax.getBoard()[0][0].setFst(false);
    stateMax.getBoard()[0][0].setSnd(8);
    List<DuidokuState> list = new LinkedList<DuidokuState>();
    list.add(stateMax);
    boolean resultado;
    DuidokuProblem problem = new DuidokuProblem(stateAux);
    //sabemos que tiene un solo elemento
    resultado = problem.getSuccessors(stateAux).get(0).equals(list.get(0));
    assertTrue(resultado); //hacemos asi porque assertEquals compara entre objetos
  }

  /**
   * Testea el metodo end.
   */
  @Test
  void testEnd() {
    boolean m = true;
    board[0][0] = new Casilla(false,8);
    DuidokuState stateAux = new DuidokuState(board,m);
    DuidokuProblem problemCopy = new DuidokuProblem(stateAux);
    assertTrue(problemCopy.end(stateAux));
  }

  /**
   * Testea el metodo value cuando es un estado final.
   */
  @Test
  void testValue1() {
    //estado final 
    board[0][0] = new Casilla(false,8);
    DuidokuState stateAux = new DuidokuState(board,true);
    DuidokuProblem problemCopy = new DuidokuProblem(stateAux);
    assertEquals(problemCopy.value(stateAux),Integer.MIN_VALUE);
  }

  /**
   * Testea el metodo value cuando no es un estado final.
   */
  @Test
  void testValue2() {
    //cuando no es estado final
    DuidokuState stateAux = new DuidokuState(board,true);
    DuidokuProblem problemCopy = new DuidokuProblem(stateAux);
    int value = problemCopy.value(stateAux);
    assertEquals(value,((stateAux.cantCasillasLibres() + 1) % 2) + stateAux.cantConflictos());
  }

}
