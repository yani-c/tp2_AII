package lightup;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


/**
 * Clase que define la configuracion y obtiene resultado
 *  mediante algortimos geneticos.
 * @author Yanina Celi y Agustin Borda
 */
public class GeneticLightUp {

  //cantidad maxima de evoluciones
  private static final int MAX_ALLOWED_EVOLUTIONS = 6000;
  


  /**
   * metodo para calcular la cantidad de casillas blancas en un tablero.
   * @pre board != null
   * @param board : tablero al que se le va a calcular cantWhite : Casilla[][]
   * @return cantidad de casillas blancas que hay en board : int
  */
  public static int cantWhite(Casilla[][] board) {
    int cant = 0;//contador
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        if (board[i][j].getFst()) { //si es una casilla blanca
          cant++;
        }
      }
    }
    return cant;
  }

  /**
   * Se guarda en board el tablero con mejor solucion encontrada.
   * @pre parametros no nulos
   * @param board : tablero sin focos : Casilla[][]
   * @return valor de verdad de encontrar una solucion optima : Casilla[][]
  */
  public static boolean definition(Casilla[][] board) throws Exception {
    //reseteo la configuracion y la creo
    Configuration.reset();
    Configuration conf = new DefaultConfiguration();
    conf.setPreservFittestIndividual(true);
    conf.setKeepPopulationSizeConstant(false);
    MakeFitnessFunction myFunc = new MakeFitnessFunction(board);// se crea la fitness function
    conf.setFitnessFunction(myFunc);//la agregamos a la configuracion
    //creamos un arreglo de genes con la cantidad de casillas blancas en board
    Gene[] sampleGenes = new Gene[cantWhite(board)];
    for (int i = 0;i < sampleGenes.length;i++) {
      //le asigna un valor aleatorio entero (entre 0 y 1)
      sampleGenes[i] = new IntegerGene(conf,0,1);
    }
    //se crea un cromosoma con los genes 
    IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
    //Se agrega el cromosoma a la configuracion
    conf.setSampleChromosome(sampleChromosome);
    conf.setPopulationSize(100);//seteamos la poblacion
    IChromosome bestSolutionSoFar = null;
    Genotype poblacion = Genotype.randomInitialGenotype(conf);//se genera la poblacion con random
    boolean optima = false;
    for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) { //se hacer evoluciones
      poblacion.evolve();
      int blancos = cantWhite(board);
      bestSolutionSoFar = poblacion.getFittestChromosome();
      //si se encontro una solucion optima
      if (bestSolutionSoFar.getFitnessValue() >= blancos) { 
        i = MAX_ALLOWED_EVOLUTIONS; //se deja de evolucionar
        optima = true;
      }
    }
    //si al salir se encontro solucion optima
    if (optima) {
      System.out.println("Solution found, improving...");
      IChromosome aux = null;
      for (int l = 0;l < 1500;l++) { //se intenta encontrar una mejor
        poblacion.evolve();
        aux = poblacion.getFittestChromosome();
        if (aux.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
          bestSolutionSoFar = aux;
        }
      }
    }
    System.out.println("Fitness value: " + bestSolutionSoFar.getFitnessValue());
    //se guarda el mejor tablero que se encontro
    Casilla[][] a = myFunc.completeBoard(bestSolutionSoFar);
    for (int i = 0;i < 7;i++) {
      for (int j = 0;j < 7;j++) {
        board[i][j] = a[i][j];
      }
    }
    return optima;
  }

  /**
   * Retorna el tablero en forma de String.
   * 
   * @pre parametro board no nulo
   * @param board : tablero que se desea imprimir : Casilla[][]
   * @return tablero en String para poder mostrarlo : String
  */
  public static String getTablero(Casilla[][] board) {
    String a = "";
    for (int i = 0;i < 7;i++) {
      a = a + "\n";
      for (int j = 0;j < 7;j++) {
        if (board[i][j].getFst()) {
          a = a + "|" + board[i][j].getSnd() + "|";
        } else {
          a = a + "|*" + board[i][j].getSnd() + "|";
        }
      }
    }
    a = a + "\n";
    return a;
  }

  /**
   * inserta una casilla negra en b en la posicion (fila,columna) con nro, si es que se puede.
   * @param fila : fila de b donde se desea insertar la casilla negra : int 
   * @param columna : columna de b donde se desea insertar la casilla negra : int
   * @param nro : restriccion que se le desea poner a la casilla negra : int
   * @param b : tablero donde se desea insertar la casilla negra : Casilla[][]
   * @return valor de verdad correspondiende a si se pudo o no insertar la casilla negra : boolean
  */
  public static boolean cargarCasillaNegra(int fila, int columna, int nro,Casilla[][] b) {
    if (b[fila][columna].getFst()) {
      int cant = 0;
      //si puedo moverme para arriba, y la de arriba es una casilla blanca 
      if (fila - 1 >= 0 && b[fila - 1][columna].getFst()) {
        cant++;
      }
      //si puedo moverme para abajo, y la de abajoa es una casilla blanca 
      if (fila + 1 < 7 && b[fila + 1][columna].getFst()) {
        cant++;
      }
      //si puedo moverme para la derecha, y esa es una casilla blanca 
      if (columna + 1 < 7 && b[fila][columna + 1].getFst()) {
        cant++;
      }
      //si puedo moverme para la izquierda, y esa es una casilla blanca 
      if (columna - 1 >= 0 && b[fila][columna - 1].getFst()) {
        cant++;
      }
      //si llas casillas blancas que tiene al rededor no son los que pide
      if ((cant > 0 && nro == -1) || (cant >= nro && nro != -1)) {
        b[fila][columna].setFst(false);
        b[fila][columna].setSnd(nro);
        return true;
      }
    }
    return false;
  }

}
