package duidoku;
 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase que representa un estado del juego "Duidoku".
 * @author Agustin Borda, Yanina Celi
 * @version 0.1 15/06/2019
 */
public class DuidokuState implements AdversarySearchState {

  private Casilla[][] board;
  private boolean max;
  private Object ruleApplied; //no la usamos, pero la ponemos porque en la interfaz esta
  
  /**
   * Construye un nuevo estado del duidoku.
   * @pre true
   * @post Existe un nuevo objeto estado 
  */
  public DuidokuState() {
    board = new Casilla[9][9];
    ruleApplied = null;
  }
  
  /**
  * Construye un nuevo estado del duidoku, especificando si es un nodo max.
  * @pre que m sea distinto de null
  * @param m : valor booleano que nos dice si el nuevo estado es max o no : boolean
   * @post Existe un nuevo objeto estado && max == m
  */
  public DuidokuState(boolean m) {
    board = new Casilla[9][9];
    max = m;
    ruleApplied = null;
  }
  
  /**
   * Construye un nuevo estado del duidoku, especificando si es un nodo max,
   *  y seteandole un tablero.
   *  @pre parametros no nulos
   * @param m : valor booleano que nos dice si el nuevo estado es max o no : boolean 
   * @param b : El tablero que setearemos : Casilla[][]
   * @post board == b && max == m
  */
  public DuidokuState(Casilla[][] b, boolean m) {
    board = b;
    max = m;
    ruleApplied = null;
  }
  
  /**
   * Construye un nuevo estado del duidoku, especificando si es un nodo max, 
   * seteandole un tablero.
   * especificando la regla de avance utilizada para llegar a este estado
   * @param m Un valor booleano que nos dice si el nuevo estado es max o no 
   * @param b El tablero que setearemos
   * @param r La regla de avance
   * @post board == b && max == m && ruleAplied == r
  */
  public DuidokuState(Casilla[][] b, boolean m, Object r) {
    board = b;
    max = m;
    ruleApplied = r;
  }
  
  /**
   * Copia en otro objeto un estado dado.
   * @pre d no nulo 
   * @param d : El estado a copiar : DuidokuState
   * @post this.equals(d) == true
  */
  public DuidokuState(DuidokuState d) {
    board = new Casilla[9][9];
    for (int i = 0;i < 9;i++) {
      for (int j = 0;j < 9;j++) {
        board[i][j] = new Casilla(d.getBoard()[i][j].getFst(),d.getBoard()[i][j].getSnd());
      }
    }
    max = d.isMax();
  }
  
  /**
   * Escribe el estado.
   * @pre estado != null
   * @return El estado como un String : String
  */
  public String toString() {
    String m;
    m = toStringBoard();
    m = m + "\n" + max;
    return m;
  }
  
  /**
   * Devuelve el tablero de un estado.
   * @pre board != null
   * @return El tablero : Casilla[][]
  */
  public Casilla[][] getBoard() {
    return board;
  }
  
  /**
   * Pasa un tablero de un estado a un String.
   * @pre board != null
   * @return El tablero como un String : String
  */
  public String toStringBoard() {
    String a = "";
    for (int i = 0;i < 9;i++) {
      a = a + "\n";
      for (int j = 0;j < 9;j++) {
        a = a + "|" + board[i][j].getSnd() + "|";
      }
    }
    a = a + "\n";
    return a;
  }

  /**
   * Nos dice si 2 estados son iguales.
   * @param other : el estado con el que compararemos nuestro objeto : State
   * @pre this != null && other != null
   * @return true ssi los 2 estados son iguales : boolean
  */
  @Override
  public boolean equals(State other) {
    DuidokuState o = (DuidokuState) other;
    boolean e = true;
    for (int i = 0;i < 9 && e;i++) {
      for (int j = 0;j < 9 && e;j++) {
        if (!board[i][j].equals(o.getBoard()[i][j])) {
          e = false;
        }
      }
    }
    return e;
  }
  
  /**
   * Devuelve nuestro atributo max.
   * @pre this!=null
   * @return nuestro atributo max : boolean
  */
  @Override
  public boolean isMax() {
    return max;
  }

  /**
   * Retorna la regla aplicada para llegar a estado actual.
  */
  @Override
  public Object ruleApplied() {
    return ruleApplied;
  }
  
  /**
    * Devuelve la cantidad de casillas que no estan ocupadas.
    * @pre this != null
    * @return cantidad de casillas que no tienen un numero ni estan bloqueadas : int
  */
  public int cantCasillasLibres() {
    int cant = 0;
    for (int i = 0;i < 9;i++) {
      for (int j = 0;j < 9;j++) {
        if (board[i][j].getFst()) {
          cant++;
        }
      }
    }
    return cant;
  }

  /**
   * Devuelve la cantidad de confllictos.
   * @pre this!= null
   * @return cantidad de conflictos en el tablero : int
   * @post todas las casillas que tengan conflictos estan bloqueadas
  */
  public int cantConflictos() {
    int cant = 0;
    for (int i = 0;i < 9;i++) {
      for (int j = 0;j < 9;j++) {
        if (board[i][j].getFst()) {
          List<Integer> list = getOptions(i,j);
          if (list.isEmpty()) {
            cant++;
            board[i][j].setFst(false);
            board[i][j].setSnd(-1);
          }
        }
      }
    }
    return cant;
  }
  
  /**
   * Dada una posicion en el tablero, calcula los numeros posibles que se pueden ingresar.
   * @pre this!= null && 0<=i<9 && 0<=i<9
   * @param i : La fila de la casilla : int
   * @param j : La columna de la casilla : int
   * @see getOptionsRow
   * @see getOptionsColumn
   * @see getOptionsSection
   * @return lista con todas las opciones validas para esa casilla : List<\Integre\>
  */
  public List<Integer> getOptions(int i,int j) {
    Set<Integer> fila = new HashSet<Integer>();
    Set<Integer> columna = new HashSet<Integer>();
    Set<Integer> seccion = new HashSet<Integer>();
    fila = getOptionsRow(i,j);
    columna = getOptionsColumn(i,j);
    seccion = getOptionsSection(i,j);
    fila.retainAll(columna);
    fila.retainAll(seccion);
    return (List<Integer>) new ArrayList<Integer>(fila);
  }
  
  /**
   * Dada una posicion en el tablero, calcula las opciones que no tienen conflictos en la fila.
   * @pre this!= null && 0<=i<9 && 0<=i<9
   * @param i : la fila de la casilla : int
   * @param j : la columna de la casilla : int
   * @return Un Conjunto con los numeros que no generan conflictos en la fila : Set<\Integer\>
  */
  public Set<Integer> getOptionsRow(int i,int j) {
    Set<Integer> estan = new HashSet<Integer>();
    for (int k = 0;k < 9;k++) {
      if (!board[i][k].getFst() && board[i][k].getSnd() != 0) {
        estan.add(board[i][k].getSnd());
      }
    }
    Set<Integer> opciones = new HashSet<Integer>();
    for (int m = 1;m < 10;m++) {
      opciones.add(m);
    }
    //borro las que ya estan en la fila
    opciones.removeAll(estan);
    return opciones;
  }
  
  /**
   * Dada una posicion en el tablero, calcula las opciones que no tienen conflictos en la columna.
   * @pre this!= null && 0<=i<9 && 0<=i<9
   * @param i : la fila de la casilla : int
   * @param j : la columna de la casilla : int
   * @return Un Conjunto con los numeros que no generan conflictos en la columna : Set<\Integer\>
  */
  public Set<Integer> getOptionsColumn(int i,int j) {
    Set<Integer> estan = new HashSet<Integer>();
    for (int k = 0;k < 9;k++) {
      if (!board[k][j].getFst() && board[k][j].getSnd() != 0) {
        estan.add(board[k][j].getSnd());
      }
    }
    Set<Integer> opciones = new HashSet<Integer>();
    for (int m = 1;m < 10;m++) {
      opciones.add(m);
    }
    //borro las que ya estan en la fila
    opciones.removeAll(estan);
    return opciones;
  }
  
  /**
   * Dada una posicion en el tablero, calcula las opciones que no tienen conflictos en la seccion.
   * @pre this!= null && 0<=i<9 && 0<=i<9
   * @param i : la fila de la casilla : int
   * @param j : la columna de la casilla : int
   * @return Un Conjunto con los numeros que no generan conflictos en la seccion : Set<\Integer\>
  */
  public Set<Integer> getOptionsSection(int i,int j) {
    int desdeFila = (i / 3) * 3;
    int desdeColumna = (j / 3) * 3;
    Set<Integer> estan = new HashSet<Integer>();
    for (int k = desdeFila;k < desdeFila + 3;k++) {
      for (int m = desdeColumna;m < desdeColumna + 3;m++) {
        if (!board[k][m].getFst() && board[k][m].getSnd() != 0) {
          estan.add(board[k][m].getSnd());
        }
      }
    }
    Set<Integer> opciones = new HashSet<Integer>();
    for (int m = 1;m < 10;m++) {
      opciones.add(m);
    }
    //borro las que ya estan en la fila
    opciones.removeAll(estan);
    return opciones;
  }
    
  /**
   * Setea el atributo max con un booleano dado.
   * @pre this != null
   * @param m : el booleano dado : boolean
   * @post max == m
  */
  @Override
  public void setMax(boolean m) {
    max = m;
  }
  
}
