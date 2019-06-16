package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lightUp.Casilla;

public class PantallaCasillaNegra extends JFrame {

	private JSpinner s= new JSpinner();
	private SpinnerNumberModel elegir= new SpinnerNumberModel();
	private JButton aceptar= new JButton("Aceptar");
	private int valor;
	
	private final JFrame frame = new JFrame("Editando casilla negra");
    private final Container grid = new Container();
	
	public PantallaCasillaNegra() {
		elegir.setMinimum(-1);
		elegir.setMaximum(4);
		s.setModel(elegir);
		aceptar.addActionListener(aceptarListener);
		s.add(aceptar);
		frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(s, BorderLayout.NORTH);
        frame.add(aceptar);
		grid.setVisible(true);
        frame.add(grid, BorderLayout.CENTER);
       // showSpinner();
	}
	
	ActionListener aceptarListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
        	valor = (Integer) s.getValue(); 
        	grid.setVisible(false);
        	//frame.setVisible(false);
        }
    };
    
    public int getValor() {
    	return valor;
    }
	
}
