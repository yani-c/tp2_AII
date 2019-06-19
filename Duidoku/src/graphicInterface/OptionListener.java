package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import duidoku.DuidokuProblem;
import duidoku.DuidokuState;
import duidoku.MinMaxAlphaBetaSearchEngine;

public class OptionListener implements ActionListener{
	private int i;
	private int j;
	private DuidokuState s;
	private View v;
	private MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine;
	
	public OptionListener(int fila,int columna,DuidokuState state, View view,MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> e) {
		i = fila;
		j = columna;
		s = state;
		v = view;
		engine = e;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String o = ((JButton)event.getSource()).getText();
	    int op = Integer.parseInt(o);
	    s.getBoard()[i][j].setFst(false);
	    s.getBoard()[i][j].setSnd(op);
	    s.setMax(true);
	    if(s.cantCasillasLibres()<=0) {
			JOptionPane.showMessageDialog(null, "Ganaste");
			System.exit(0);
	    }
	    else {
	    	if(s.cantCasillasLibres()<30) {
	    		engine.setDepth(2);
	    	}
	    	else {
	    		if(s.cantCasillasLibres() <50) {
	    			engine.setDepth(1);
	    		}
	    	}
	    }
	    s = engine.computeSuccessor(s);
	    v.setState(s);
	    v.getFrame().removeAll();
	    v.getOptionFrame().setVisible(false);
	    v.getFrame().setVisible(false);
	    v.draw();
	    if(s.cantCasillasLibres()<=0) {
			JOptionPane.showMessageDialog(null, "Perdiste");
			System.exit(0);
	    }
	}
}
