package lightUp;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import vista.PantallaPrincipal;


public class GeneticLightUp{

	//cantidad maxima de evoluciones
	  private static final int MAX_ALLOWED_EVOLUTIONS = 6000;
	  
	  //main donde se llama a la interfaz grafica
	  public static void main(String[] args) throws Exception {
		  PantallaPrincipal p= new PantallaPrincipal();
		  
	  }
	  
	  /**
	   * @param board : tablero al que se le va a calcular cantWhite : Casilla[][]
	   * @return cantidad de casillas blancas que hay en board : int
	   */
	  public static int cantWhite(Casilla[][] board) {
		  int cant=0;//contador
		  for(int i=0;i<7;i++) {
			  for(int j=0;j<7;j++) {
				  if(board[i][j].getFst()) {//si es una casilla blanca
					  cant++;
				  }
			  }
		  }
		  return cant;
	  }
	  
	  /**
	   * Se guarda en board el tablero con mejor solucion encontrada 
	   * @param board: tablero sin focos : Casilla[][]
	   * @return valor de verdad de encontrar una solucion optima : Casilla[][]
	   */
	  public static boolean definition(Casilla[][] board) throws Exception {
		  //reseteo la configuracion y la creo
		  Configuration.reset();
		  Configuration conf = new DefaultConfiguration();
		  conf.setPreservFittestIndividual(true);
		  conf.setKeepPopulationSizeConstant(false);
		  makeFitnessFunction myFunc = new makeFitnessFunction(board);// se crea la fitness function
		  conf.setFitnessFunction(myFunc);//la agregamos a la configuracion
		//creamos un arreglo de genes con la cantidad de casillas blancas en board
		  Gene[] sampleGenes = new Gene[cantWhite(board)];
		  for(int i=0;i<sampleGenes.length;i++) {
			  //le asigna un valor aleatorio entero (entre 0 y 1)
			  sampleGenes[i]= new IntegerGene(conf,0,1);
		  }
		  //se crea un cromosoma con los genes 
		  IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		  //Se agrega el cromosoma a la configuracion
		  conf.setSampleChromosome(sampleChromosome);
		  conf.setPopulationSize(100);//seteamos la poblacion
		  IChromosome bestSolutionSoFar = null ;
		  Genotype poblacion = Genotype.randomInitialGenotype(conf);//se genera la poblacion con random
		 boolean optima=false;
		  for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {//se hacer evoluciones
		      poblacion.evolve();
		      bestSolutionSoFar = poblacion.getFittestChromosome();
		      if(bestSolutionSoFar.getFitnessValue() >= cantWhite(board)) { //si se encontro una solucion optima
		    	  i = MAX_ALLOWED_EVOLUTIONS;//se deja de evolucionar
		    	  optima=true;
		      }
		  }
		//si al salir se encontro solucion optima
		  if(optima){
			  System.out.println("Solution found, improving...");
			  IChromosome aux=null;
			  for(int l = 0; l<1500;l++) {//se intenta encontrar una mejor
				  poblacion.evolve();
				  aux=poblacion.getFittestChromosome();
				  if(aux.getFitnessValue()>bestSolutionSoFar.getFitnessValue()) {
					  bestSolutionSoFar=aux;
				  }
			  }
		  }
		  System.out.println("Fitness value: "+bestSolutionSoFar.getFitnessValue());
		  Casilla[][] a=myFunc.completeBoard(bestSolutionSoFar); //se guarda el mejor tablero que se encontro
		  for(int i=0;i<7;i++) {
			  for(int j=0;j<7;j++) {
				  board[i][j]=a[i][j];
			  }
		  }
		  return optima;
	  }
	  
	  /**
	   * @param board : tablero que se desea imprimir : Casilla[][]
	   * @return tablero en String para poder mostrarlo : String
	   */
	  public static String getTablero(Casilla[][] board) {
		  String a="";
		  for(int i=0;i<7;i++) {
			  a=a+"\n";
			  for(int j=0;j<7;j++) {
				  if(board[i][j].getFst()) {
					  a=a+"|"+board[i][j].getSnd()+"|";
				  }
				  else {
					  a=a+"|*"+board[i][j].getSnd()+"|";
				  }
			  }
		  }
		  a=a+"\n";
		  		return a;
	  }
	  
	  
	
}
