package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameButtonListener implements ActionListener{
	private JButton[][] grid;
	private View v;
	
	public GameButtonListener(JButton[][] g, View vi) {
		grid = g;
		v = vi;
	}
	
    @Override
    public void actionPerformed(ActionEvent event) {
    	boolean found = false;
        for (int i = 0; i < 9 && !found; i++) {
        	for (int j = 0; j < 9 && !found; j++) {
        		found = event.getSource().equals(grid[i][j]);
        		if (found) {
        			v.optionScreen(i,j);
                }
            }
        }
    }
      
}
