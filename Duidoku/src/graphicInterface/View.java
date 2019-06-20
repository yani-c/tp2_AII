package graphicInterface;
import java.awt.*;
import javax.swing.*;
import duidoku.DuidokuProblem;
import duidoku.DuidokuState;
import duidoku.MinMaxAlphaBetaSearchEngine;

/**
 * Clase que implementa una interfaz grafica que representa un estado del juego "Duidoku"
 * @author Agustin Borda, Yanina Celi
 * @version 0.1 17/06/2019
 */

public class View {
	private DuidokuState s;
    private JButton[][] grid; 
	private Container cont;
	private JFrame frame;
	private JFrame f;
	DuidokuProblem p;
	private MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine;
	/**
	 * Constructor de la interfaz
	 * @param st el estado a representar
	 * @param e el motor de busqueda
	 * @param problem el problema del cual salio st
	 */
    public View(DuidokuState st,MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> e, DuidokuProblem problem) {
    	engine = e;
    	engine.setDepth(0);
    	s = new DuidokuState(st);
    	grid = new JButton[9][9];
    	p = problem;
    }
	/**
	 * Get de frame
	 * @pre this != null
	 * @return el atributo frame
	 */
    public JFrame getFrame() {
    	return frame;
    }
	/**
	 * Get de f
	 * @pre this != null
	 * @return el atributo f
	 */
    public JFrame getOptionFrame() {
    	return f;
    }
    /**
     * Setea nuestro atributo s con un estado dado
     * @param st el estado dado
     * @post s == st
     */
    public void setState(DuidokuState st) {
    	s = st;
    }
    /**
     * Metodo que dibuja un tablero que representa al atributo s
     * @pre s != null
     */
	public void draw() {
		frame = new JFrame("Duidoku");
		cont = new Container();
		cont.setLayout(new GridLayout(9,9));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(70*9+4,70*9+48);
		Font font1 = new Font("SansSerif", Font.BOLD, 30);
	    for(int i=0; i<9; i++) {
	    	for (int j=0;j<9;j++) {
		    	grid[i][j]= new JButton();/*Creamos una matriz de botones*/
	    		grid[i][j].setFont(font1);
	    		grid[i][j].setOpaque(true);
	    		if(s.getBoard()[i][j].getFst()) {
	    			grid[i][j].setBackground(java.awt.Color.white);
		    		GameButtonListener gameButtonListener = new GameButtonListener(grid,this);
		    		grid[i][j].addActionListener(gameButtonListener);
	    		}/*Si se pueden llenar, les asignamos la accion de los botones del tablero*/
	    		else {
	    			if(s.getBoard()[i][j].getSnd() ==-1) {
	    				grid[i][j].setBackground(java.awt.Color.darkGray);
	    			}/*Si estan bloqueados, les ponemos un color oscuro*/
	    			else {
	    				grid[i][j].setText(s.getBoard()[i][j].getSnd().toString());
	    			}/*Si ya estan escritos, escribimos el numero que tienen en el estado*/
	    		}
                grid[i][j].setFocusPainted(false);
	            cont.add(grid[i][j]);/*Agregamos los botones al contenedor*/
	    	}
	    }
        frame.add(this.cont, BorderLayout.CENTER);/*Agregamos el contenedor al frame*/
	    frame.setVisible(true);/*dibujamos el frame*/
	}
	/**
	 * Metodo que dada una posicion, dibuja una ventana con las opciones disponibles para llenar esa casilla
	 * @pre 0<=i<9 && 0<=j<9           
	 * @param i la fila de la casilla
	 * @param j la columna de la casilla
	 */
	public void optionScreen(int i,int j) {
	   JButton[] op = new JButton[s.getOptions(i, j).size()];/*Creamos un arreglo de botones*/
	   Container c = new Container();
	   c.setLayout(new GridLayout(1,s.getOptions(i, j).size()));
	   f = new JFrame("Options");
	   f.setSize(50*s.getOptions(i, j).size(), 70);/*creamos la ventana*/
	   for(int k = 0; k < s.getOptions(i, j).size();k++) {
		   op[k] = new JButton(s.getOptions(i,j).get(k).toString());
		   OptionListener optionListener = new OptionListener(i,j,s,this,engine,p);
		   op[k].addActionListener(optionListener);
		   /*A cada boton del arreglo le asignamos una accion que se realizara cuando sean presionados*/
		   c.add(op[k]);/*Agregamos los botones al container*/
	   }
	   f.add(c);/*agregamos el container al frame de opcion*/
	   f.setVisible(true); /*dibujamos el frame de opcion*/
   }
	
	
}