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
	public void testEvaluateIChromosome() {
		fail("Not yet implemented");
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
		      //Genotype poblacion = new Genotype(conf, new Population(conf, cromosomas));
			//Genotype poblacion = new Genotype(conf, new Population(conf, c));
			System.out.println("Toy por evolucionar");
		      Genotype poblacion = Genotype.randomInitialGenotype(conf);
			  for (int i = 0; i < 50; i++) {
			      poblacion.evolve();
			  }
			  System.out.println("ya evlucione");
			  IChromosome bestSolutionSoFar = poblacion.getFittestChromosome();
			System.out.println("HOLA SOY UN CROMOSOMA "+bestSolutionSoFar.toString());
			System.out.println("HOLA SOY SU PRIM ELEMENTO "+bestSolutionSoFar.getGene(0).getAllele());
			System.out.println("iluminadas : "+myFunc.getCantIluminadas(bestSolutionSoFar));
	}

	@Test
	public void testIluminarTablero() {
		fail("Not yet implemented");
	}

	@Test
	public void testIluminarArriba() {
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
		
		
		fail("Not yet implemented");
	}

	@Test
	public void testIluminarAbajo() {
		fail("Not yet implemented");
	}

	@Test
	public void testIluminarDerecha() {
		fail("Not yet implemented");
	}

	@Test
	public void testIluminarIzquierda() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompleteBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCantConflictos() {
		fail("Not yet implemented");
	}

	@Test
	public void testConflictosLugar() {
		fail("Not yet implemented");
	}

	@Test
	public void testConflictosCasillasNegras() {
		fail("Not yet implemented");
	}

}
