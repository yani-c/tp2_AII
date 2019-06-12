package lightUp;

import org.jgap.*;
import org.jgap.impl.*;

public class GeneticLightUp{

	
	  private static final int MAX_ALLOWED_EVOLUTIONS = 120;
	  
	  public static void main(String[] args) throws Exception {
			Casilla blanca= new Casilla(true,0);
			Casilla[][] aux={
				{blanca,blanca,new Casilla(false,-1),blanca,blanca,blanca,blanca,},
				{blanca,new Casilla(false,-1),blanca,blanca,blanca,new Casilla(false,4),blanca},
				{blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca,new Casilla(false,-1)},
				{blanca,blanca,new Casilla(false,1),blanca,new Casilla(false,-1),blanca,blanca},
				{new Casilla(false,-1),blanca,blanca,new Casilla(false,3),blanca,blanca,blanca},
				{blanca,new Casilla(false,3),blanca,blanca,blanca,new Casilla(false,2),blanca},
				{blanca,blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca}};
			//poner a definition en un while, mientras no sea 37 o llegue a cierta cantidad de iteraciones
			definition(aux);
	  }
	  
	  public static void definition(Casilla[][] board) throws Exception {
		  Configuration conf = new DefaultConfiguration();
		  conf.setPreservFittestIndividual(true);
		  conf.setKeepPopulationSizeConstant(false);
		  makeFitnessFunction myFunc = new makeFitnessFunction(board);
		  conf.setFitnessFunction(myFunc);
		  Gene[] sampleGenes = new Gene[49];
		  for(int i=0;i<sampleGenes.length;i++) {
		  sampleGenes[i]= new IntegerGene(conf,0,1);
		  }
		  IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		  conf.setSampleChromosome(sampleChromosome);
		  conf.setPopulationSize(10);
		  IChromosome[] cromosomas = new IChromosome[conf.getPopulationSize()];
		  Gene[] muestra_genes = sampleChromosome.getGenes();
	      for (int i = 0; i < cromosomas.length; i++) {
	    	  Gene[] genes = new Gene[muestra_genes.length];
	    	  for (int k = 0; k < genes.length; k++) {
	    		  genes[k] = muestra_genes[k].newGene();
	    	  }
	    	  cromosomas[i] = new Chromosome(conf, genes);
	      }
		  Genotype poblacion = Genotype.randomInitialGenotype(conf);
		  for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
		      poblacion.evolve();
		  }
		  IChromosome bestSolutionSoFar = poblacion.getFittestChromosome();
		  System.out.println("fitness : "+bestSolutionSoFar.getFitnessValue());
		  Casilla[][] tablerito= myFunc.completeBoard(bestSolutionSoFar);
		  System.out.println(getTablero(tablerito));
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
