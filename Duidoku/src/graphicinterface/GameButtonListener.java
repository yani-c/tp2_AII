package graphicinterface;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Clase que implementa una accion que se ejecutara cuando se aprete un boton
 *  en el tablero del juego.
 * @author Agustin Borda, Yanina Celi
 * @version 0.1 17/06/2019
 */

public class GameButtonListener implements ActionListener {

  private JButton[][] grid;
  
  private View view;

  /**
   * Constructor de la accion.
   * @param g : el tablero de botones con el que representamos el juego : JButton[][]
   * @param vi : la interfaz : View
  */
  public GameButtonListener(JButton[][] g, View vi) {
    grid = g;
    view = vi;
  }
  
  /**
   * Accion que se ejecutara cuando se presione un boton.
  */
  @Override
  public void actionPerformed(ActionEvent event) {
    boolean found = false;
    for (int i = 0; i < 9 && !found; i++) {
      for (int j = 0; j < 9 && !found; j++) { /*Buscamos el boton que llamo a la accion*/
        found = event.getSource().equals(grid[i][j]);
        if (found) {
          view.optionScreen(i,j); /*Creamos una ventana on las opciones*/
        }
      }
    }
  }
      
}
