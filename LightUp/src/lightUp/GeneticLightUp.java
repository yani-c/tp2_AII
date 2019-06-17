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

	
	  private static final int MAX_ALLOWED_EVOLUTIONS = 6000;
	  
	  public static void main(String[] args) throws Exception {
		  PantallaPrincipal p= new PantallaPrincipal();
		  
	  }
	  
	  public static int cantWhite(Casilla[][] board) {
		  int cant=0;
		  for(int i=0;i<7;i++) {
			  for(int j=0;j<7;j++) {
				  if(board[i][j].getFst()) {
					  cant++;
				  }
			  }
		  }
		  return cant;
	  }
	  
	  public static Casilla[][] definition(Casilla[][] board) throws Exception {
		  Configuration.reset();
		  Configuration conf = new DefaultConfiguration();
		  conf.setPreservFittestIndividual(true);
		  conf.setKeepPopulationSizeConstant(false);
		  makeFitnessFunction myFunc = new makeFitnessFunction(board);
		  conf.setFitnessFunction(myFunc);
		  Gene[] sampleGenes = new Gene[cantWhite(board)];
		  for(int i=0;i<sampleGenes.length;i++) {
			  sampleGenes[i]= new IntegerGene(conf,0,1);
		  }
		  IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		  conf.setSampleChromosome(sampleChromosome);
		  conf.setPopulationSize(100);
		  IChromosome bestSolutionSoFar = null ;
		  Genotype poblacion = Genotype.randomInitialGenotype(conf);
		  for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
		      poblacion.evolve();
		      bestSolutionSoFar = poblacion.getFittestChromosome();
		      if(bestSolutionSoFar.getFitnessValue() >= cantWhite(board)) {
		    	  i = MAX_ALLOWED_EVOLUTIONS;
		      }
		  }
		  if(bestSolutionSoFar.getFitnessValue() >= cantWhite(board)){
			  System.out.println("Solution found, improving...");
			  for(int l = 0; l<1500;l++) {
				  poblacion.evolve();
			  }
		  }
		  System.out.println("Fitness value: "+bestSolutionSoFar.getFitnessValue());
		  return myFunc.completeBoard(bestSolutionSoFar);
	  }
	  
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
