package graphicInterface;
import java.awt.*;
import javax.swing.*;
import duidoku.DuidokuProblem;
import duidoku.DuidokuState;
import duidoku.MinMaxAlphaBetaSearchEngine;



public class View {
	private DuidokuState s;
    private JButton[][] grid; 
	private Container cont;
	private JFrame frame;
	private JFrame f;
	private MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> engine;
	
    public View(DuidokuState st,MinMaxAlphaBetaSearchEngine<DuidokuProblem, DuidokuState> e) {
    	engine = e;
    	engine.setDepth(0);
    	s = new DuidokuState(st);
    	grid = new JButton[9][9];
    }
	
    public JFrame getFrame() {
    	return frame;
    }
    
    public JFrame getOptionFrame() {
    	return f;
    }
    
    public void setState(DuidokuState st) {
    	s = st;
    }
    
	public void draw() {
		frame = new JFrame("Duidoku");
		cont = new Container();
		cont.setLayout(new GridLayout(9,9));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(70*9+4,70*9+48);
		Font font1 = new Font("SansSerif", Font.BOLD, 30);
	    for(int i=0; i<9; i++) {
	    	for (int j=0;j<9;j++) {
		    	grid[i][j]= new JButton();
	    		grid[i][j].setFont(font1);
	    		grid[i][j].setOpaque(true);
	    		if(s.getBoard()[i][j].getFst()) {
	    			grid[i][j].setBackground(java.awt.Color.white);
		    		GameButtonListener gameButtonListener = new GameButtonListener(grid,this);
		    		grid[i][j].addActionListener(gameButtonListener);
	    		}
	    		else {
	    			if(s.getBoard()[i][j].getSnd() ==-1) {
	    				grid[i][j].setBackground(java.awt.Color.darkGray);
	    			}
	    			else {
	    				grid[i][j].setText(s.getBoard()[i][j].getSnd().toString());
	    			}
	    		}
                grid[i][j].setFocusPainted(false);
	            cont.add(grid[i][j]);
	    	}
	    }
        frame.add(this.cont, BorderLayout.CENTER);
	    frame.setVisible(true);
	}
	            
   public void optionScreen(int i,int j) {
	   JButton[] op = new JButton[s.getOptions(i, j).size()];
	   Container c = new Container();
	   c.setLayout(new GridLayout(1,s.getOptions(i, j).size()));
	   f = new JFrame("Options");
	   f.setSize(50*s.getOptions(i, j).size(), 70);
	   for(int k = 0; k < s.getOptions(i, j).size();k++) {
		   op[k] = new JButton(s.getOptions(i,j).get(k).toString());
		   OptionListener optionListener = new OptionListener(i,j,s,this,engine);
		   op[k].addActionListener(optionListener);
		   c.add(op[k]);
	   }
	   f.add(c);
	   f.setVisible(true);
   }
	
	
}