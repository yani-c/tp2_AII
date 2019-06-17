package graphicInterface;

import duidoku.DuidokuState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
	DuidokuState s;
    JButton[][] grid; 
	Container cont;
    
    public View(DuidokuState st) {
    	s = new DuidokuState(st);
    	grid = new JButton[9][9];
		cont = new Container();
		cont.setLayout(new GridLayout(9,9));
		Font font1 = new Font("SansSerif", Font.BOLD, 30);
	    for(int i=0; i<9; i++) {
	    	for (int j=0;j<9;j++) {
		    	grid[i][j]= new JButton();
	    		grid[i][j].setFont(font1);
	    		grid[i][j].setOpaque(true);
	    		if(s.getBoard()[i][j].getFst()) {
	    			grid[i][j].setBackground(java.awt.Color.white);
	    		}
	    		else {
	    			grid[i][j].setBackground(java.awt.Color.gray);
	    		}
	    		grid[i][j].addActionListener(gameButtonListener);
	            cont.add(grid[i][j]);
	    	}
	    }
    }
	
	public DuidokuState draw() {
		JFrame frame = new JFrame("Duidoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(50*9+4,50*9+48); 	    	
        frame.add(this.cont, BorderLayout.CENTER);
	    frame.setVisible(true);
		return this.s;
	}
	
	
    
    ActionListener gameButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            boolean condition = false;
            boolean found = false;
            int x = 0, y = 0, nro=-10;
            for (int i = 0; i < 9 && !found; i++) {
                for (int j = 0; j < 9 && !found; j++) {
                    found = event.getSource().equals(grid[i][j]);
                    if (found) {
                    	//casillaNegra.setVisible(true);
                    	//nro=casillaNegra.getValor();
                    	//casillaNegra.setVisible(false);
                    	System.out.println("soy nro "+i+" "+j);
                    }
                }
            }
            if (condition) {
            	System.out.println("soy "+s.getBoard()[x][y].getFst());
                s.getBoard()[x][y].setFst(false);
                //tablero[x][y].setSnd(nro);
              //  refreshWindow();
                //ver si ta creo
                }
            }//endif
        };//endMethod

    
	
	
	
}