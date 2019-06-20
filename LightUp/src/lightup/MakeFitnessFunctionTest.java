package lightup;

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

/**
 * Clase para testear los metodos de makeFitnessFunciton.
 * @author Yanina Celi y Agustin Borda
 */
public class MakeFitnessFunctionTest {

  private Casilla[][] board = {
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
    {new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),
      new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)}
  };

  /**
   * se testea el metodo "evaluate".
  */
  @Test
  public void testEvaluate() throws InvalidConfigurationException {
    board[0][0] = new Casilla(true,1);;
    Configuration.reset();
    Configuration conf = new DefaultConfiguration();
    conf.setPreservFittestIndividual(true);
    conf.setKeepPopulationSizeConstant(false);
    MakeFitnessFunction myFunc = new MakeFitnessFunction(board);
    conf.setFitnessFunction(myFunc);
    Gene[] sampleGenes = new Gene[37]; // 37 es la cantidad de blancas
    for (int i = 0;i < sampleGenes.length;i++) {
      sampleGenes[i] = new IntegerGene(conf,0,1);
    }
    IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
    conf.setSampleChromosome(sampleChromosome);
    conf.setPopulationSize(100);
    IChromosome bestSolutionSoFar = null;
    Genotype poblacion = Genotype.randomInitialGenotype(conf);
    for (int i = 0; i < 6000; i++) {
      poblacion.evolve();
      bestSolutionSoFar = poblacion.getFittestChromosome();
      if (bestSolutionSoFar.getFitnessValue() >= 37) {
        i = 6000;
      }
    }
    if (bestSolutionSoFar.getFitnessValue() >= 37) {
      System.out.println("Solution found, improving...");
      for (int l = 0; l < 1500;l++) {
        poblacion.evolve();
      }
    }
    System.out.println("Fitness value: " + bestSolutionSoFar.getFitnessValue());
  }

  /**
   * se testea el metodo "estaIluminada".
  */
  @Test
  public void testestaIluminada() throws InvalidConfigurationException {
    assertTrue(MakeFitnessFunction.estaIluminada(0,2,board));
  }



  /**
   * se testea el metodo "conflictosCasillasNegras".
  */
  @Test
  public void testConflictosCasillasNegras() throws InvalidConfigurationException {
    board[0][0] = new Casilla(false,0);
    board[0][1] = new Casilla(true,1);
    assertEquals(1,MakeFitnessFunction.conflictosCasillasNegras(board));
  }

  /**
   * se testea el metodo "conflictosLugar".
  */
  @Test
  public void testConflictosLugar() throws InvalidConfigurationException {
    board[0][0] = new Casilla(true,1);
    board[0][1] = new Casilla(true,1);
    assertEquals(1,MakeFitnessFunction.conflictosLugar(board));
  }

}
