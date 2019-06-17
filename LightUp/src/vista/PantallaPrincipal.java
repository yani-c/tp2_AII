package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lightUp.Casilla;
import lightUp.GeneticLightUp;
import lightUp.makeFitnessFunction;

public class PantallaPrincipal extends JFrame {
	
	private Casilla[][] tablero= new Casilla[7][7];;
	
	private final JFrame frame = new JFrame("Light Up");
    private JButton[][] buttons = new JButton[7][7];
    private ImageIcon atomIcon;
    private final JPanel menu = new JPanel();
    private final JPanel casillasNegras = new JPanel();
    private final Container grid = new Container();
    private final JButton newGame = new JButton("Juego nuevo");
    private final JButton exit = new JButton("Salir");
    private final JButton obtenerSolucion = new JButton("Obtener solucion");
	
	private JSpinner s= new JSpinner();
	private SpinnerNumberModel elegir= new SpinnerNumberModel();
    
	public PantallaPrincipal() {
		menu.setLayout(new FlowLayout());
		menu.setBackground(java.awt.Color.pink);
        menu.add(newGame, FlowLayout.LEFT);
        menu.add(exit,FlowLayout.CENTER);
        menu.add(obtenerSolucion,FlowLayout.RIGHT);
        newGame.addActionListener(newGameListener);
        exit.addActionListener(exitListener);
        obtenerSolucion.addActionListener(solucionListener);

        frame.setSize(50*7+4,50*7+48);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menu, BorderLayout.NORTH);
		////////////////////////////7
        grid.setLayout(new GridLayout(7,7));
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBackground(java.awt.Color.white);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(gameButtonListener);
                grid.add(buttons[i][j]);
                
            }
        }
        grid.setVisible(true);
        frame.add(grid, BorderLayout.CENTER);
	}
	
	public void inicializar(Casilla[][] c) {
		for(int i=0;i<c.length;i++) {
			for(int j=0;j<c.length;j++) {
				c[i][j]=new Casilla(true,0);
			}
		}
	}
	

	ActionListener exitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    };
    
    ActionListener newGameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
        	inicializar(tablero);
            //
        	casillasNegras.setLayout(new FlowLayout());
        	JLabel e= new JLabel("Focos");
            elegir.setMinimum(-1);
    		elegir.setMaximum(4);
    		s.setModel(elegir);
    		casillasNegras.add(e);
    		casillasNegras.add(s);
    		casillasNegras.setBackground(java.awt.Color.pink);
    		s.setVisible(true);
    		frame.add(casillasNegras, BorderLayout.LINE_END);
        	//
            refreshWindow();              
        }
    };
    
    ActionListener solucionListener  = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
				tablero= GeneticLightUp.definition(tablero);
			} catch (Exception e) {
				e.printStackTrace();
			}
            refreshWindow();
        }
    };
    
    ActionListener gameButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            boolean condition = false;
            boolean found = false;
            int x = 0, y = 0, nro=-10;
    		nro= (Integer) s.getValue();
            for (int i = 0; i < buttons.length && !found; i++) {
                for (int j = 0; j < buttons[0].length && !found; j++) {
                    found = event.getSource().equals(buttons[i][j]);
                    if (found) {
                        condition = ((nro > -2) && (nro<5));
                        x = i;
                        y = j;
                    }
                }
            }
            if (condition) {
                tablero[x][y].setFst(false);
                tablero[x][y].setSnd(nro);
                refreshWindow();
                }
            }//endif
        };//endMethod

    
    public void refreshWindow() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                int aux = tablero[i][j].getSnd();
                if(tablero[i][j].getFst()) {
                	if (aux == 1) {
	                    atomIcon = new ImageIcon("src/imagenes/foco.png");
	                    ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                	}
                	else {
                		if(makeFitnessFunction.estaIluminada(i,j,tablero)) {
	                		atomIcon = new ImageIcon("src/imagenes/iluminada.png");
	                		ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
	                        buttons[i][j].setIcon(iconoEscala);
                		}
                		else {
                			atomIcon = new ImageIcon();
		                    buttons[i][j].setIcon(atomIcon);
                		}
                	}
                } else {
                    if (aux == -1) {
                        atomIcon = new ImageIcon("src/imagenes/nada.png");
                        ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                    }else if (aux == 0) {
                        atomIcon = new ImageIcon("src/imagenes/cero.png");
                        ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                    }else if (aux == 1) {
                        atomIcon = new ImageIcon("src/imagenes/uno.png");
                        ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                    } else if (aux == 2) {
                        atomIcon = new ImageIcon("src/imagenes/dos.png");
                        ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                    } else if (aux == 3) {
                        atomIcon = new ImageIcon("src/imagenes/tres.png");
                        ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                    } else if (aux == 4) {
                        atomIcon = new ImageIcon("src/imagenes/cuatro.png");
                        ImageIcon iconoEscala = new ImageIcon(atomIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT));
                        buttons[i][j].setIcon(iconoEscala);
                    }
                }
            }
        }
    }

}
