package lightUp;

import static org.junit.Assert.*;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.impl.BulkFitnessOffsetRemover;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.junit.Test;

public class makeFitnessFunctionTest {

	/*  PARA TABLERO BASICO
	Casilla blanca= new Casilla(true,0);
	Casilla[][] aux={
		{blanca,blanca,new Casilla(false,-1),blanca,blanca,blanca,blanca,},
		{blanca,new Casilla(false,-1),blanca,blanca,blanca,new Casilla(false,4),blanca},
		{blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca,new Casilla(false,-1)},
		{blanca,blanca,new Casilla(false,1),blanca,new Casilla(false,-1),blanca,blanca},
		{new Casilla(false,-1),blanca,blanca,new Casilla(false,3),blanca,blanca,blanca},
		{blanca,new Casilla(false,3),blanca,blanca,blanca,new Casilla(false,2),blanca},
		{blanca,blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca}
	};
	 */
	
	@Test
	public void testEvaluateIChromosome() throws InvalidConfigurationException {
		Casilla blanca= new Casilla(true,0);
		Casilla iluminada=new Casilla(true,1);
		Casilla[][] board={
			{iluminada,blanca,new Casilla(false,-1),blanca,blanca,blanca,blanca,},
			{blanca,new Casilla(false,-1),blanca,blanca,blanca,new Casilla(false,4),blanca},
			{blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca,new Casilla(false,-1)},
			{blanca,blanca,new Casilla(false,1),blanca,new Casilla(false,-1),blanca,blanca},
			{new Casilla(false,-1),blanca,blanca,new Casilla(false,3),blanca,blanca,blanca},
			{blanca,new Casilla(false,3),blanca,blanca,blanca,new Casilla(false,2),blanca},
			{blanca,blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca}
						};
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
			  for (int i = 0; i < 50; i++) {
			      poblacion.evolve();
			  }
			  IChromosome bestSolutionSoFar = poblacion.getFittestChromosome();			  
			System.out.println("fitness : "+myFunc.evaluate(bestSolutionSoFar));
	}

	@Test
	public void testGetCantIluminadas() throws InvalidConfigurationException {
		Casilla blanca= new Casilla(true,0);
		Casilla iluminada=new Casilla(true,1);
		Casilla[][] board={
			{iluminada,blanca,new Casilla(false,-1),blanca,blanca,blanca,blanca,},
			{blanca,new Casilla(false,-1),blanca,blanca,blanca,new Casilla(false,4),blanca},
			{blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca,new Casilla(false,-1)},
			{blanca,blanca,new Casilla(false,1),blanca,new Casilla(false,-1),blanca,blanca},
			{new Casilla(false,-1),blanca,blanca,new Casilla(false,3),blanca,blanca,blanca},
			{blanca,new Casilla(false,3),blanca,blanca,blanca,new Casilla(false,2),blanca},
			{blanca,blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca}
						};
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
			System.out.println("Toy por evolucionar");
		      Genotype poblacion = Genotype.randomInitialGenotype(conf);
			  for (int i = 0; i < 50; i++) {
			      poblacion.evolve();
			  }
			  System.out.println("ya evlucione");
			  IChromosome bestSolutionSoFar = poblacion.getFittestChromosome();
			  System.out.println("iluminadas : "+myFunc.getCantIluminadas(bestSolutionSoFar));
	}



	@Test
	public void testGetCantConflictos() throws InvalidConfigurationException {
		Casilla blanca= new Casilla(true,0);
		Casilla iluminada=new Casilla(true,1);
		Casilla[][] board={
			{iluminada,blanca,new Casilla(false,-1),blanca,blanca,blanca,blanca,},
			{blanca,new Casilla(false,-1),blanca,blanca,blanca,new Casilla(false,4),blanca},
			{blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca,new Casilla(false,-1)},
			{blanca,blanca,new Casilla(false,1),blanca,new Casilla(false,-1),blanca,blanca},
			{new Casilla(false,-1),blanca,blanca,new Casilla(false,3),blanca,blanca,blanca},
			{blanca,new Casilla(false,3),blanca,blanca,blanca,new Casilla(false,2),blanca},
			{blanca,blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca}
						};
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
			System.out.println("Toy por evolucionar");
		      Genotype poblacion = Genotype.randomInitialGenotype(conf);
			  for (int i = 0; i < 50; i++) {
			      poblacion.evolve();
			  }
			  System.out.println("ya evlucione");
			  IChromosome bestSolutionSoFar = poblacion.getFittestChromosome();		  
			System.out.println("conflictos : "+myFunc.getCantConflictos(bestSolutionSoFar));
	}

}
