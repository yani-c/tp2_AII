package lightUp;

import org.jgap.*;
import org.jgap.impl.*;

public class GeneticLightUp{

	
	  //private static final int MAX_ALLOWED_EVOLUTIONS = 50;
	  
	  public static void main(String[] args) {
		  
	  }
	
	  public static int cantWhite(Pair<Boolean,Integer>[][] board) {
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
	  
	  public static void definition(Pair<Boolean,Integer>[][] board) throws Exception {
		  Configuration conf = new DefaultConfiguration();
		  conf.setPreservFittestIndividual(true);
		  conf.setKeepPopulationSizeConstant(false);
		  FitnessFunction myFunc = new makeFitnessFunction(cantWhite(board));
		  conf.setBulkFitnessFunction(new BulkFitnessOffsetRemover(myFunc));
		  conf.setFitnessFunction(myFunc);
		  Gene[] sampleGenes = new Gene[2];
		  //IntegerGene gene = new IntegerGene(conf,0,1);
		  //gene.setConstraintChecker(new EnergyGeneConstraintChecker());
		  sampleGenes[0]= IntegerGene gene = new IntegerGene(conf,0,0);
		  sampleGenes[1]= IntegerGene gene = new IntegerGene(conf,0,1);
		  IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		  conf.setSampleChromosome(sampleChromosome);
		  conf.setPopulationSize(10);
		  Genotype population = Genotype.randomInitialGenotype(conf);
		  for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
		      population.evolve();
		  }
		  IChromosome bestSolutionSoFar = population.getFittestChromosome();
	  }
	  
	  
	
}
