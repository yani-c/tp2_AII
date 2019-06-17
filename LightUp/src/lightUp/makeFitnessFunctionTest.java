package lightUp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
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
			{iluminada,blanca,new Casilla(false,-1),blanca,blanca,blanca,blanca},
			{blanca,new Casilla(false,-1),blanca,blanca,blanca,new Casilla(false,4),blanca},
			{blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca,new Casilla(false,-1)},
			{blanca,blanca,new Casilla(false,1),blanca,new Casilla(false,-1),blanca,blanca},
			{new Casilla(false,-1),blanca,blanca,new Casilla(false,3),blanca,blanca,blanca},
			{blanca,new Casilla(false,3),blanca,blanca,blanca,new Casilla(false,2),blanca},
			{blanca,blanca,blanca,blanca,new Casilla(false,-1),blanca,blanca}
						};
		  Configuration.reset();
		  Configuration conf = new DefaultConfiguration();
		  conf.setPreservFittestIndividual(true);
		  conf.setKeepPopulationSizeConstant(false);
		  makeFitnessFunction myFunc = new makeFitnessFunction(board);
		  conf.setFitnessFunction(myFunc);
		  Gene[] sampleGenes = new Gene[37];// 37 es la cantidad de blancas
		  for(int i=0;i<sampleGenes.length;i++) {
		  sampleGenes[i]= new IntegerGene(conf,0,1);
		  }
		  IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		  conf.setSampleChromosome(sampleChromosome);
		  conf.setPopulationSize(100);
		  IChromosome bestSolutionSoFar = null ;
		  Genotype poblacion = Genotype.randomInitialGenotype(conf);
		  for (int i = 0; i < 6000; i++) {
		      poblacion.evolve();
		      bestSolutionSoFar = poblacion.getFittestChromosome();
		      if(bestSolutionSoFar.getFitnessValue() >= 37) {
		    	  i = 6000;
		      }
		  }
		  if(bestSolutionSoFar.getFitnessValue() >= 37){
			  System.out.println("Solution found, improving...");
			  for(int l = 0; l<1500;l++) {
				  poblacion.evolve();
			  }
		  }
		  System.out.println("Fitness value: "+bestSolutionSoFar.getFitnessValue());
	}

	@Test
	public void testestaIluminada() throws InvalidConfigurationException {
		Casilla[][] board={
				{new Casilla(true,1),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)}
							};
		  assertTrue(makeFitnessFunction.estaIluminada(0,2,board));
	}



	@Test
	public void testConflictosCasillasNegras() throws InvalidConfigurationException {
		Casilla[][] board={
			{new Casilla(false,0),new Casilla(true,1),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)}
						};
		assertEquals(1,makeFitnessFunction.conflictosCasillasNegras(board));
	}

	@Test
	public void testConflictosLugar() throws InvalidConfigurationException {
		Casilla[][] board={
			{new Casilla(true,1),new Casilla(true,1),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
			{new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)}
						};
		assertEquals(1,makeFitnessFunction.conflictosLugar(board));
	}


}
