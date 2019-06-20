package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import duidoku.DuidokuProblem;
import duidoku.DuidokuState;
import duidoku.MinMaxAlphaBetaSearchEngine;
/**
 * Clase que implementa una accion que se le agrega a los botones de opcion en la interfaz
 * @author Yanina Celi, Agustin Borda
 *@version 0.1 16/06/2019
 */
public class OptionListener implements ActionListener{
	private int i;
	private int j;
	private DuidokuState s;
	private View v;
	DuidokuProblem p;
	private MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine;
	/**
	 * Constructor por defecto
	 * @pre ninguno de los parametros es null
	 * @param fila fila del boton al que se le implementara la accion
	 * @param columna columna del boton al que se le implementara la accion
	 * @param state estado que es representado por la interfaz
	 * @param view la interfaz
	 * @param e el motor de busqueda
	 * @param problem el problema de busqueda
	 */
	public OptionListener(int fila,int columna,DuidokuState state, View view,MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> e,	DuidokuProblem problem) {
		i = fila;
		j = columna;
		s = state;
		v = view;
		engine = e;
		p = problem;
	}
	/**
	 * Metodo que se ejecutara cuando se presione uno de los botones de opciones
	 * @pre this !=null
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String o = ((JButton)event.getSource()).getText();/*Recuperamos la opcion del boton*/
	    int op = Integer.parseInt(o);
	    s.getBoard()[i][j].setFst(false);
	    s.getBoard()[i][j].setSnd(op);/*actualizamos el estado con la opcion que eligio el usuario*/
	    s.setMax(true);
	    if(p.end(s)) {
			JOptionPane.showMessageDialog(null, "Ganaste");
			System.exit(0);
	    }/*Si con la opcion que el usuario puso, el estado se convierte en un estado final, quiere decir que el usuario gano*/
	    else {
	    	if(s.cantCasillasLibres()<30) {
	    		engine.setDepth(2);
	    	}
	    	else {
	    		if(s.cantCasillasLibres() <50) {
	    			engine.setDepth(1);
	    		}/*Por cuestiones de eficiencia,conforme se vayan reduciendo las casillas libres, aumentaremos la profundidad a la que se ejecuta el algoritmo*/
	    	}
	    }
	    s = engine.computeSuccessor(s);/*Computamos el sucesor utilizando MinMax con poda alfa-beta*/
	    v.setState(s);/*Actualizamos el estado*/
	    v.getFrame().removeAll();
	    v.getOptionFrame().setVisible(false);
	    v.getOptionFrame().dispose();
	    v.getFrame().setVisible(false);/*Borramos nuestra interfaz*/
	    v.draw();/*Creamos una nueva con los valores actualizados*/
	    if(p.end(s)) {
			JOptionPane.showMessageDialog(null, "Perdiste");
			System.exit(0);
	    }/*Si el estado se convirtio en un estado final al insertar la maquina un valor, quiere decir que esta gano*/
	}
}
