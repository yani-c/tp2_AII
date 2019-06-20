package graphicinterface;

import duidoku.DuidokuProblem;
import duidoku.DuidokuState;
import duidoku.MinMaxAlphaBetaSearchEngine;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Clase que implementa una interfaz grafica que representa un estado del
 * juego "Duidoku".
 * @author Agustin Borda, Yanina Celi
 * @version 0.1 17/06/2019
 */

public class View {

  private DuidokuState state;
  private JButton[][] grid; 
  private Container cont;
  private JFrame frame;
  private JFrame frame2;
  DuidokuProblem problem;
  private MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine;

  /**
   * Constructor de la interfaz.
   * @param st : el estado a representar : DuidokuState
   * @param e : el motor de busqueda : MinMaxAlphaBetaSearchEngine<\DuidokuProblem, DuidokuState\>
   * @param problem : el problema del cual salio st : DuidokuProblem
  */
  public View(DuidokuState st,MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> e,
      DuidokuProblem problem) {
    engine = e;
    engine.setDepth(0);
    state = new DuidokuState(st);
    grid = new JButton[9][9];
    this.problem = problem;
  }
  
  /**
   * Get de frame.
   * @pre this != null
   * @return el atributo frame : JFrame
  */
  public JFrame getFrame() {
    return frame;
  }
  
  /**
   * Get de frame2.
   * @pre this != null
   * @return el atributo frame2 : JFrame
  */
  public JFrame getOptionFrame() {
    return frame2;
  }
  
  /**
   * Setea nuestro atributo state con un estado dado.
   * @param st : el estado dado : DuidokuState
   * @post s == st
  */
  public void setState(DuidokuState st) {
    state = st;
  }
  
  /**
   * Metodo que dibuja un tablero que representa al atributo state.
   * @pre state != null
  */
  public void draw() {
    frame = new JFrame("Duidoku");
    cont = new Container();
    cont.setLayout(new GridLayout(9,9));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(70 * 9 + 4,70 * 9 + 48);
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    for (int i = 0; i < 9; i++) {
      for (int j = 0;j < 9;j++) {
        grid[i][j] = new JButton(); /*Creamos una matriz de botones*/
        grid[i][j].setFont(font1);
        grid[i][j].setOpaque(true);
        if (state.getBoard()[i][j].getFst()) {
          grid[i][j].setBackground(java.awt.Color.white);
          GameButtonListener gameButtonListener = new GameButtonListener(grid,this);
          grid[i][j].addActionListener(gameButtonListener);
          /*Si se pueden llenar, les asignamos la accion de los botones del tablero*/
        } else {
          if (state.getBoard()[i][j].getSnd() == -1) {
            grid[i][j].setBackground(java.awt.Color.darkGray);
            /*Si estan bloqueados, les ponemos un color oscuro*/
          } else {
            grid[i][j].setText(state.getBoard()[i][j].getSnd().toString());
          } /*Si ya estan escritos, escribimos el numero que tienen en el estado*/
        }
        grid[i][j].setFocusPainted(false);
        cont.add(grid[i][j]); /*Agregamos los botones al contenedor*/
      }
    }
    frame.add(this.cont, BorderLayout.CENTER); /*Agregamos el contenedor al frame*/
    frame.setVisible(true); /*dibujamos el frame*/
  }
  
  /**
   * Metodo que dada una posicion, dibuja una ventana con las opciones 
   * disponibles para llenar esa casilla.
   * @pre 0<=i<9 && 0<=j<9           
   * @param i : la fila de la casilla : int
   * @param j : la columna de la casilla : int
  */
  public void optionScreen(int i,int j) {
    JButton[] op = new JButton[state.getOptions(i, j).size()]; /*Creamos un arreglo de botones*/
    Container c = new Container();
    c.setLayout(new GridLayout(1,state.getOptions(i, j).size()));
    frame2 = new JFrame("Options");
    frame2.setSize(50 * state.getOptions(i, j).size(), 70); /*creamos la ventana*/
    for (int k = 0; k < state.getOptions(i, j).size();k++) {
      op[k] = new JButton(state.getOptions(i,j).get(k).toString());
      OptionListener optionListener = new OptionListener(i,j,state,this,engine,problem);
      op[k].addActionListener(optionListener);
      /*A cada boton del arreglo le asignamos una accion que se realizara cuando sean presionados*/
      c.add(op[k]); /*Agregamos los botones al container*/
    }
    frame2.add(c); /*agregamos el container al frame de opcion*/
    frame2.setVisible(true); /*dibujamos el frame de opcion*/
  }


}