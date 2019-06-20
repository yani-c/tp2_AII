package lightup;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 * Clase para definir la fitnness function.
 * @author Yanina Celi y Agustin Borda
 */
public class MakeFitnessFunction extends FitnessFunction {

  //Tablero que sirve de auxiliar para calcular la funcion fitness
  private final Casilla[][] board;

  /**
   * Contructor, inicializa el atributo "board".
   * @param b : tablero que se le asigna al atributo "board" : Casilla[][]
  */
  public MakeFitnessFunction(Casilla[][] b) {
    board = b;
  }
    
  /**
   *Fitness function (evalua un cromosoma).
   * @pre board != null && c != null
   * @param c : el cromosoma a evaluar : IChromosome
   * @see getCantFocos
   * @see getCantIluminadas
   * @see getCantConflictos
   * @return valor asignado por la funcion fitness : double
  */
  @Override
  protected double evaluate(IChromosome c) {
    int cantFocos = getCantFocos(c);//calculo la cantidad de focos
    int cantIluminadas = getCantIluminadas(c);//Calculo la cantidad de casillas iluminadas
    int cantConflictos = getCantConflictos(c); //Calculo la cantidad de conflictos
    return ((cantIluminadas + 0.0) / (0.0 + cantConflictos + 1) + (1.0 / (cantFocos + 1.0)));
  }
 
  /**
   * se ponen los elementos de c en el tablero board 
   * (en las casillas blancas del mismo) y se cuentan la cantidad de focos que hay. 
   * @pre board != null && c != null
   * @param c : cromosoma a evaluar en la funcion fitness : IChromosome
   * @return cantidad de focos que hay en el cromosoma : int
  */
  public int getCantFocos(IChromosome c) {
    int focos = 0; //contador 
    for (Gene g : c.getGenes()) { 
      //si el gen es un 1, significa que hay foco, si hay 0 que no
      focos += (Integer)g.getAllele();
    }
    return focos;
  }

  /**
   * Retorna la cantidad de focos que tiene el tablero b.
   * @pre board != null && c != null
   * @param b : tablero al que se le va a calcular la cantidad de focos : Casilla[][]
   * @return cantidad de focos que tiene el tablero b : int
   */
  public static int getFocos(Casilla[][] b) {
    int focos = 0;
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        if (b[i][j].getFst() && b[i][j].getSnd() == 1) {
          focos++;
        }
      }
    }
    return focos;
  }

  /**
   * Se ponen los elementos de c en el tablero board
   *  (en las casillas blancas del mismo) y se calculan las casilas iluminadas.
   *  @pre board != null && c != null
   * @param c : cromosoma a evaluar en la funcion fitness : IChromosome
   * @see inicializarTablero
   * @see iluminarTablero
   * @return cantidad de casillas iluminadas (focos incluidos) en el tablero board : int
  */
  public int getCantIluminadas(IChromosome c) {
    Boolean[][] iluminadas = new Boolean[7][7];
    Casilla[][] aux = completeBoard(c);
    inicializarTablero(iluminadas); //inicializo la matriz con "false"
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        //si es una casilla blanca y tiene foco
        if ((aux[i][j].getFst()) && (aux[i][j].getSnd() > 0)) {
          //iluminamos tablero
          iluminarTablero(aux,iluminadas,i,j);
        }
      } 
    }
    int cant = 0;
    for (int k = 0;k < 7;k++) {
      for (int m = 0;m < 7;m++) {
        if (iluminadas[k][m]) { //si hay "true" en la casilla
          cant++; //sumo 
        }
      }
    }
    return cant;
  }

  /**
   * se crea una matriz de booleanos, donde se marca con true 
   * las posiciones que en el tablero board estan iluminadas. 
   * luego se informa si la casilla de la posicion (fila,columna) esta iluminada
   * @pre c != null && 0<=i<7 && 0<=j<7
   * @param fila : fila de c en donde se encuentra la casilla a tratar : int
   * @param columna : columna de c en donde se encuentra la casilla a tratar : int
   * @param c : tablero donde se encuentra la casilla a tratar : Casilla[][]
   * @see inicializarTablero
   * @see iluminarTablero
   * @return valor de verdad de que esta iluminada la casilla en (fila,columna) de c : boolean
  */
  public static boolean estaIluminada(int fila,int columna,Casilla[][] c) {
    Boolean[][] iluminadas = new Boolean[7][7];
    inicializarTablero(iluminadas);//inicializo la matriz en "false"
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        //si es una casilla blanca y tiene foco
        if ((c[i][j].getFst()) && (c[i][j].getSnd() > 0)) {
          //iluminamos tablero
          iluminarTablero(c,iluminadas,i,j);
        }
      }
    }
    return iluminadas[fila][columna];
  }
 
  /**
   * inicializa la matriz iluminadas con falso.
   * @pre true
   * @param iluminadas : matriz a inicializar con "false" : Boolean[][]
  */
  public static void inicializarTablero(Boolean[][] iluminadas) {
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        iluminadas[i][j] = false;
      }
    }
  }

  /**
   * Pone true en la matriz iluminadas,
   *  dependiendo si la casilla de esa posicion,  en board , 
   *  esta iluminada por el foco que hay en (i,j).
   * @param board : tablero con focos y casillas negras : Casilla[][]
   * @param iluminadas : matriz de booleanos a modificar, inicializada en "false" : Boolean[][]
   * @param i : fila de board donde esta el foco : int
   * @param j : columna de board donde esta el foco : int
   * @see iluminarArriba
   * @see iluminarAbajo
   * @see iluminarDerecha
   * @see iluminarIzquierda
  */
  public static void iluminarTablero(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
    iluminarArriba(board,iluminadas,i,j);
    iluminarAbajo(board,iluminadas,i,j);
    iluminarDerecha(board,iluminadas,i,j);
    iluminarIzquierda(board,iluminadas,i,j);
  }

  /**
   * ilumina las casillas blancas que esten arriba del foco que se encuentra
   *  en board en la posicion (i,j) (antes que haya una casilla negra).
   * @param board : tablero donde se encuentra el foco : Casilla[][]
   * @param iluminadas : matriz que se va a modificar : Boolean[][]
   * @param i : fila de board donde esta en foco : int
   * @param j : columna de board donde esta el foco : int
  */
  public static void iluminarArriba(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
    //mientras exista la casilla y sea blanca
    while (i >= 0 && board[i][j].getFst()) { 
      iluminadas[i][j] = true; //ilumino
      i--; //subo
    }
  }

  /**
   * ilumina las casillas blancas que esten abajo del foco que se 
   * encuentra en board en la posicion (i,j) (antes que haya una casilla negra).
   * @param board : tablero donde se encuentra el foco : Casilla[][]
   * @param iluminadas : matriz que se va a modificar : Boolean[][]
   * @param i : fila de board donde esta en foco : int
   * @param j : columna de board donde esta el foco : int
  */
  public static void iluminarAbajo(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
    //mientras exista la casilla y sea blanca
    while (i < 7 && board[i][j].getFst()) {
      iluminadas[i][j] = true; //ilumino
      i++; //bajo
    }
  }

  /**
   * ilumina las casillas blancas que esten a la derecha del foco que se 
   * encuentra en board en la posicion (i,j) (antes que haya una casilla negra).
   * @param board : tablero donde se encuentra el foco : Casilla[][]
   * @param iluminadas : matriz que se va a modificar : Boolean[][]
   * @param i : fila de board donde esta en foco : int
   * @param j : columna de board donde esta el foco : int
  */
  public static void iluminarDerecha(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
    //mientras exista la casilla y sea blanca
    while (j < 7 && board[i][j].getFst()) {
      iluminadas[i][j] = true; //ilumino
      j++; //me muevo a la derecha
    }
  }

  /**
   * ilumina las casillas blancas que esten a la izquierda del foco que se 
   * encuentra en board en la posicion (i,j) (antes que haya una casilla negra).
   * @param board : tablero donde se encuentra el foco : Casilla[][]
   * @param iluminadas : matriz que se va a modificar : Boolean[][]
   * @param i : fila de board donde esta en foco : int
   * @param j : columna de board donde esta el foco : int
  */
  public static void iluminarIzquierda(Casilla[][] board, Boolean[][] iluminadas, int i,int j) {
    //mientras exista la casilla y sea blanca
    while (j >= 0 && board[i][j].getFst()) {
      iluminadas[i][j] = true; // ilumino
      j--; // me muevo a la izquierda
    }
  }

  /**
   * crea un tablero apartir de "board" y pone en las casillas blancas del mismo, 
   * los elementos del cromosoma c.
   * @param c : cromosoma a tratar : IChromosome
   * @return tablero board con los elementos de c : Casilla[][]
  */
  public Casilla[][] completeBoard(IChromosome c) {
    Casilla[][] boardAux = new Casilla[7][7];
    int k = 0;
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        if (board[i][j].getFst()) { //si es una casilla blanca
          //le paso a boardAux una casilla con el valor del cromosoma c
          boardAux[i][j] = new Casilla(true,(Integer) c.getGene(k).getAllele());
          k++;
        } else {
          //si es una casilla negra, la paso a boardAux una casilla igual
          boardAux[i][j] = board[i][j];
        }
      }
    }
    return boardAux;
  }

  /**
   * calcula la cantidad de conflictos que hay en board,
   *  si se le agrega el cromosoma c .
   * @param c :  cromosoma a tratar : IChromosome
   * @see conflictosLugar
   * @see conflictosCasillasNegras
   * @see completeBoard
   * @return cantidad de conflictos que hay en board al agregar los elementos de c : int
  */ 
  public int getCantConflictos(IChromosome c) {
    Casilla[][] aux = completeBoard(c);
    int cant = conflictosLugar(aux);
    cant = cant + conflictosCasillasNegras(aux);
    return cant;
  }


  /**
   * calcula los conflictos de lugar 
   *(si hay mas de un foco en la misma fila o columna) en el tablero aux.
   * @param aux : tablero a tratar : Casilla[][]
   * @return cantidad de conflictos de lugar en aux : int
  */
  public static int conflictosLugar(Casilla[][] aux) {
    int conflictos = 0;
    //miro las filas
    for (int i = 0;i < 7;i++) {
      boolean hayFoco = false;
      for (int j = 0;j < 7;j++) {
        //si es blanca, tiene foco y no encontre foco en la misma linea que ilumina
        if (aux[i][j].getFst() && aux[i][j].getSnd() == 1 && !hayFoco) {
          hayFoco = true;
        //si es negra y habia foco
        } else if (!aux[i][j].getFst() && hayFoco) {
          hayFoco = false;
        //si es blanca, tiene foco y habia foco antes
        } else if (aux[i][j].getFst() && aux[i][j].getSnd() == 1 && hayFoco) {
          conflictos++;
        }
      }
    }
    //miro las columnas
    for (int i = 0;i < 7;i++) {
      boolean hayFoco = false;
      for (int j = 0;j < 7;j++) {
        //si es blanca, tiene foco y no encontre foco en la misma columna que ilumina
        if (aux[j][i].getFst() && aux[j][i].getSnd() == 1 &&  !hayFoco) {
          hayFoco = true;
        //si es negra y habia foco
        } else if (!aux[j][i].getFst() && hayFoco) {
          hayFoco = false;
        //si es blanca, tiene foco y habia foco antes
        } else if (aux[j][i].getFst() && aux[j][i].getSnd() == 1 && hayFoco) {
          conflictos++;
        }
      }
    }
    return conflictos;
  }


  /**
   * calcula la cantidad de conflictos con casillas negras en aux
   * (si hay alguna casilla negra que tenga mas o menos focos de los que inidica).
   * @param aux : tablero a tratar : Casilla[][]
   * @return cantidad de conflictos con casillas negras en aux : int
  */
  public static int conflictosCasillasNegras(Casilla[][] aux) {
    int conflictos = 0;
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        if (!aux[i][j].getFst() && aux[i][j].getSnd() >= 0) {
          int focos = 0;
          //si puedo moverme para arriba, y la de arriba es una casilla blanca y si tiene  foco
          if (i - 1 >= 0 && aux[i - 1][j].getFst() && aux[i - 1][j].getSnd() == 1) {
            focos++;
          }
          //si puedo moverme para abajo, y la de abajoa es una casilla blanca y si tiene  foco
          if (i + 1 < 7 && aux[i + 1][j].getFst() && aux[i + 1][j].getSnd() == 1) {
            focos++;
          }
          //si puedo moverme para la derecha, y esa es una casilla blanca y si tiene  foco
          if (j + 1 < 7 && aux[i][j + 1].getFst() && aux[i][j + 1].getSnd() == 1) {
            focos++;
          }
          //si puedo moverme para la izquierda, y esa es una casilla blanca y si tiene  foco
          if (j - 1 >= 0 && aux[i][j - 1].getFst() && aux[i][j - 1].getSnd() == 1) {
            focos++;
          }
          //si los focos que tiene no son los que pide
          if (focos != aux[i][j].getSnd()) {
            //si son mas
            if (focos > aux[i][j].getSnd()) {
              //calculo la cantidad de conflictos en este caso
              int conf = focos - aux[i][j].getSnd(); 
              conflictos = conflictos + conf; //sumo
            } else { //si son menos
              //calculo la cantidad de conflictos en este caso
              int conf = aux[i][j].getSnd() - focos;
              conflictos = conflictos + conf; //sumo
            }
          }
        }
      }
    }
    return conflictos;
  }


}
