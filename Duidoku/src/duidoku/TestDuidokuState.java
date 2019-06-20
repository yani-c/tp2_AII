package duidoku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Clase para testear la clase "DuidokuState".
 * @author Agustin Borda y Yanina Celi
 */
class TestDuidokuState {

	/**
	 * Atributo usado para testear en los metodos.
	 */
  private Casilla[][] board = {
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)},
      {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
        new Casilla(true,0)}};


  /**
   * Testea el metodo casillasLibres.
   */
  @Test
  void testCantCasillasLibres() {
    board[0][1] = new Casilla(false,1);
    board[1][0] = new Casilla(false,2);
    board[1][1] = new Casilla(false,3);
    DuidokuState state = new DuidokuState(board,true);
    assertEquals(78,state.cantCasillasLibres());
  }

  /**
   * Testea el metodo cantConflictos.
   */
  @Test
  void testCantConflictos() {
    board[0][0] = new Casilla(false,1);
    board[0][1] = new Casilla(false,5);
    board[0][2] = new Casilla(false,6);
    board[1][0] = new Casilla(false,2);
    board[1][1] = new Casilla(false,9);
    board[1][3] = new Casilla(false,8);
    board[1][4] = new Casilla(false,3);
    board[2][1] = new Casilla(false,4);
    board[2][2] = new Casilla(false,7);
    board[3][0] = new Casilla(false,5);
    board[4][0] = new Casilla(false,9);
    board[5][0] = new Casilla(false,7);
    board[6][0] = new Casilla(false,6);
    board[7][0] = new Casilla(false,3);
    board[8][0] = new Casilla(false,8);
    DuidokuState state = new DuidokuState(board,true);
    assertEquals(2,state.cantConflictos());
  }

  /**
   * Testea el metodo getOptions.
   */
  @Test
  void testGetOptions() {
    board[0][0] = new Casilla(true,0);
    board[0][1] = new Casilla(false,1);
    board[0][2] = new Casilla(true,0);
    board[1][0] = new Casilla(false,2);
    board[1][1] = new Casilla(false,3);
    board[1][3] = new Casilla(true,0);
    board[1][4] = new Casilla(true,0);
    board[2][1] = new Casilla(true,0);
    board[2][2] = new Casilla(true,0);
    board[3][0] = new Casilla(true,0);
    board[4][0] = new Casilla(true,0);
    board[5][0] = new Casilla(true,0);
    board[6][0] = new Casilla(true,0);
    board[7][0] = new Casilla(true,0);
    board[8][0] = new Casilla(true,0);
    DuidokuState state = new DuidokuState(board,true);
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 4;i < 10;i++) {
      list.add(i);
    }
    assertEquals(list,state.getOptions(0, 0));
  }

  /**
   * Testea el metodo getOptionsRow.
   */
  @Test
  void testGetOptionsRow() {
    board[0][0] = new Casilla(true,0);
    board[0][1] = new Casilla(false,1);
    board[0][2] = new Casilla(true,0);
    board[1][0] = new Casilla(true,0);
    board[1][1] = new Casilla(true,0);
    board[1][3] = new Casilla(true,0);
    board[1][4] = new Casilla(true,0);
    board[2][1] = new Casilla(true,0);
    board[2][2] = new Casilla(true,0);
    board[3][0] = new Casilla(true,0);
    board[4][0] = new Casilla(true,0);
    board[5][0] = new Casilla(true,0);
    board[6][0] = new Casilla(true,0);
    board[7][0] = new Casilla(true,0);
    board[8][0] = new Casilla(true,0);
    DuidokuState state = new DuidokuState(board,true);
    Set<Integer> list = new HashSet<Integer>();
    for (int i = 2;i < 10;i++) {
      list.add(i);
    }
    assertEquals(list,state.getOptionsRow(0, 0));
  }

  /**
   * Testea el metodo getOptionsColum.
   */
  @Test
  void testGetOptionsColumn() {
    board[0][0] = new Casilla(true,0);
    board[0][1] = new Casilla(true,0);
    board[0][2] = new Casilla(true,0);
    board[1][0] = new Casilla(false,3);
    board[1][1] = new Casilla(true,0);
    board[1][3] = new Casilla(true,0);
    board[1][4] = new Casilla(true,0);
    board[2][1] = new Casilla(true,0);
    board[2][2] = new Casilla(true,0);
    board[3][0] = new Casilla(true,0);
    board[4][0] = new Casilla(true,0);
    board[5][0] = new Casilla(true,0);
    board[6][0] = new Casilla(true,0);
    board[7][0] = new Casilla(true,0);
    board[8][0] = new Casilla(true,0);
    Set<Integer> list = new HashSet<Integer>();
    list.add(1);
    list.add(2);
    for (int i = 4;i < 10;i++) {
      list.add(i);
    }
    DuidokuState state = new DuidokuState(board,true);
    assertEquals(list,state.getOptionsColumn(0, 0));
  }

  /**
   * Testea el metodo getOptionSection.
   */
  @Test
  void testGetOptionsSection() {
    board[0][0] = new Casilla(true,0);
    board[0][1] = new Casilla(false,2);
    board[0][2] = new Casilla(true,0);
    board[1][0] = new Casilla(true,0);
    board[1][1] = new Casilla(true,0);
    board[1][3] = new Casilla(true,0);
    board[1][4] = new Casilla(true,0);
    board[2][1] = new Casilla(true,0);
    board[2][2] = new Casilla(true,0);
    board[3][0] = new Casilla(true,0);
    board[4][0] = new Casilla(true,0);
    board[5][0] = new Casilla(true,0);
    board[6][0] = new Casilla(true,0);
    board[7][0] = new Casilla(true,0);
    board[8][0] = new Casilla(true,0);
    DuidokuState state = new DuidokuState(board,true);
    Set<Integer> list = new HashSet<Integer>();
    list.add(1);
    for (int i = 3;i < 10;i++) {
      list.add(i);
    }
    assertEquals(list,state.getOptionsSection(0, 0));
  }


}
