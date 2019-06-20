package graphicinterface;

import duidoku.DuidokuProblem;
import duidoku.DuidokuState;
import duidoku.MinMaxAlphaBetaSearchEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Clase que implementa una accion que se le agrega a los botones de opcion en
 *  la interfaz.
 * @author Yanina Celi, Agustin Borda
 *@version 0.1 16/06/2019
 */
public class OptionListener implements ActionListener {

  private int index;
  private int index2;
  private DuidokuState state;
  private View view;
  DuidokuProblem problem;
  private MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine;

  /**
   * Constructor por defecto.
   * @pre ninguno de los parametros es null
   * @param fila : fila del boton al que se le implementara la accion : int
   * @param columna : columna del boton al que se le implementara la accion :int
   * @param state : estado que es representado por la interfaz : DuidokuState
   * @param view : la interfaz : View
   * @param e : el motor de busqueda : 
   *     MinMaxAlphaBetaSerachEngine<\DuidokuProblem, DuidokuState\>
   * @param problem : el problema de busqueda : DuidokuProblem
  */
  public OptionListener(int fila,int columna,DuidokuState state, View view,
      MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> e,DuidokuProblem problem) {
    index = fila;
    index2 = columna;
    this.state = state;
    this.view = view;
    engine = e;
    this.problem = problem;
  }
  
  /**
   * Metodo que se ejecutara cuando se presione uno de los botones de opciones.
   * @pre this !=null
  */
  @Override
  public void actionPerformed(ActionEvent event) {
    String o = ((JButton)event.getSource()).getText(); /*Recuperamos la opcion del boton*/
    int op = Integer.parseInt(o);
    state.getBoard()[index][index2].setFst(false);
    /*actualizamos el estado con la opcion que eligio el usuario*/
    state.getBoard()[index][index2].setSnd(op);
    state.setMax(true);
    if (problem.end(state)) {
      view.draw();/*Creamos una nueva con los valores actualizados*/
      JOptionPane.showMessageDialog(null, "Ganaste");
      System.exit(0);
      /*Si con la opcion que el usuario puso, el estado se convierte en un estado
       *  final, quiere decir que el usuario gano*/
    } else {
      if (state.cantCasillasLibres() < 30) {
        engine.setDepth(2);
      } else {
        if (state.cantCasillasLibres() < 50) {
          engine.setDepth(1);
          /*Por cuestiones de eficiencia,conforme se vayan reduciendo las casillas
           *  libres, aumentaremos la profundidad a la que se ejecuta el algoritmo*/
        }
      }
    }
    /*Computamos el sucesor utilizando MinMax con poda alfa-beta*/
    state = engine.computeSuccessor(state);
    view.setState(state); /*Actualizamos el estado*/
    view.getFrame().removeAll();
    view.getOptionFrame().setVisible(false);
    view.getOptionFrame().dispose();
    view.getFrame().setVisible(false); /*Borramos nuestra interfaz*/
    view.draw();/*Creamos una nueva con los valores actualizados*/
    if (problem.end(state)) {
      JOptionPane.showMessageDialog(null, "Perdiste");
      System.exit(0);
      /*Si el estado se convirtio en un estado final al insertar la maquina un valor,
       *  quiere decir que esta gano*/
    }
  }
}
