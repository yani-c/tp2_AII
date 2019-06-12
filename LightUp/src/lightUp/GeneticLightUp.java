package lightUp;

import org.jgap.*;
import org.jgap.impl.*;

public class GeneticLightUp{

	
	  private static final int MAX_ALLOWED_EVOLUTIONS = 50;
	  
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
		  definition(aux);
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
	  
	  public static void definition(Casilla[][] board) throws Exception {
		  Configuration conf = new DefaultConfiguration();
		  conf.setPreservFittestIndividual(true);
		  conf.setKeepPopulationSizeConstant(false);
		  FitnessFunction myFunc = new makeFitnessFunction(board);
		  conf.setFitnessFunction(myFunc);
		  Gene[] sampleGenes = new Gene[49];
		  //IntegerGene gene = new IntegerGene(conf,0,1);
		  //gene.setConstraintChecker(new EnergyGeneConstraintChecker());
		  for(int i=0;i<sampleGenes.length;i++) {
		  sampleGenes[i]= new IntegerGene(conf,0,1);
		  //genes[i].setAllele(new Integer(i));
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
	             // genes[k].setAllele(muestra_genes[k].getAllele());
	    	  }
	    	  cromosomas[i] = new Chromosome(conf, genes);
	      }
	      Genotype poblacion = new Genotype(conf, new Population(conf, cromosomas));
	      /*
	      IChromosome mejor_cromosoma = null;
	        for(int num_evoluciones = 0; (num_evoluciones < MAX_ALLOWED_EVOLUTIONS || !evolucion_iterada) && evolucionActivada; num_evoluciones++){
	            poblacion.evolve();
	            mejor_cromosoma = poblacion.getFittestChromosome();
	            CodificadorGenotipo.obtenerInstancia().cambiarSolucion(mejor_cromosoma);
	            if(mejor_cromosoma.getFitnessValue() == maxCruzamiento()){
	                evolucionActivada = false;
	            }
	        }
	        num_evoluciones --;*/
	      ////
		  //Genotype population = Genotype.randomInitialGenotype(conf);
		  for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
		      poblacion.evolve();
		  }
		  IChromosome bestSolutionSoFar = poblacion.getFittestChromosome();
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
