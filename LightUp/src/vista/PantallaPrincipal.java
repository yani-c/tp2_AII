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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lightup.Casilla;
import lightup.GeneticLightUp;
import lightup.MakeFitnessFunction;

/**
 * Clase para la interfaz grafica del juego light up.
 * @author Yanina Celi y Agustin Borda
 *
 */
public class PantallaPrincipal extends JFrame {

  private final JFrame frame = new JFrame("Light Up");

  //Se utiliza para que no se pueda pedir solucion sin empezar juego
  private boolean inicioJuego;

  //Tablero de juego
  private Casilla[][] tablero = new Casilla[7][7];
  
  //Se utliza para hacer el tablero
  private final Container grid = new Container();
  
  //Se utiliza para mostrar tablero
  private JButton[][] buttons = new JButton[7][7];
  
  //Se utiliza para ponerle imagenes al tablero
  private ImageIcon icon;
  
  //Se utiliza para hacer un menu
  private final JPanel menu = new JPanel();
  
  //botones del menu
  private final JButton newGame = new JButton("Juego nuevo");
  private final JButton exit = new JButton("Salir");
  private final JButton obtenerSolucion = new JButton("Obtener solucion");
  
  //se utiliza para hacer el menu de eleccion de numero para casilla negra
  private final JPanel casillasNegras = new JPanel();
  private JSpinner spinner = new JSpinner();
  private SpinnerNumberModel elegir = new SpinnerNumberModel();
  private JLabel label = new JLabel();

  /**
   * Contructor por defecto.
  */
  public PantallaPrincipal() {
    inicioJuego = false;
    //se crea un panel "menu"
    menu.setLayout(new FlowLayout());
    menu.setBackground(java.awt.Color.pink);
    menu.add(newGame, FlowLayout.LEFT); //se agregan el boton "juego nuevo"
    menu.add(obtenerSolucion,FlowLayout.CENTER); //se agrega el boton "obtener solucion
    menu.add(exit,FlowLayout.RIGHT); //se agrefa el boton "salir"
    //se le agregan las acciones a los botones
    newGame.addActionListener(newGameListener);
    exit.addActionListener(exitListener);
    obtenerSolucion.addActionListener(solucionListener);
    
    //configuro frame
    frame.setSize(50 * 7 + 4,50 * 7 + 48);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //agrego "menu" al frame
    frame.add(menu, BorderLayout.NORTH);
    //creo el "tablero" de botones
    grid.setVisible(false);
    grid.setLayout(new GridLayout(7,7));
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[0].length; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].setOpaque(true);
        buttons[i][j].setBackground(java.awt.Color.white);
        buttons[i][j].setFocusPainted(false);
        buttons[i][j].addActionListener(gameButtonListener);//le agrego accion
        grid.add(buttons[i][j]);       
      }
    }
    frame.add(grid, BorderLayout.CENTER); //agrego tablero al frame
     
    //Se configura el panel "casillasNegras"
    casillasNegras.setLayout(new FlowLayout());
    label = new JLabel("Focos");
    elegir.setMinimum(-1);
    elegir.setMaximum(4);
    spinner.setModel(elegir);
    casillasNegras.add(label); //agrego etiqueta
    casillasNegras.add(spinner); // y spinner para elegir el numero a poner en la casilla negra
    casillasNegras.setBackground(java.awt.Color.pink);
    casillasNegras.setVisible(false);
    frame.add(casillasNegras, BorderLayout.LINE_END);//agrego el panel al frame
  }

  /**
   * Inicializa el tablero con casillas blancas vacias.
  */
  public void inicializar() {
    for (int i = 0;i < tablero.length;i++) {
      for (int j = 0;j < tablero.length;j++) {
        tablero[i][j] = new Casilla(true,0);
      }
    }
  }

  /**
   * Accion que se ejecuta al presionar el boton "salir".
  */
  ActionListener exitListener = new ActionListener() {
    @Override
     public void actionPerformed(ActionEvent event) {
      inicioJuego = false;
      System.exit(0);
    }
  };
    
  /**
   * Accion que se ejecuta al presionar el boton "nuevo juego".
  */
  ActionListener newGameListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      inicioJuego = true;
      inicializar(); 
      grid.setEnabled(true);
      casillasNegras.setVisible(true);
      grid.setVisible(true);
      refreshWindow();              
    }
  };
    
  /**
   * Accion que se ejecuta al presionar el boton "Obtener solucion".
  */
  ActionListener solucionListener  = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      if (!inicioJuego) {
        JOptionPane.showMessageDialog(null, "Debe empezar un juego para obtener la solucion");
      } else {
        boolean optima = false;
        try {
          //traigo el mejor tablero encontrado
          optima = GeneticLightUp.definition(tablero);
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (!optima) {
          JOptionPane.showMessageDialog(null, "La solucion dada no es optima");
        }
        refreshWindow();
      }
    }
  };
    
  /**
   * Accion que se ejecuta al presionar algun boton del tablero "buttons".
  */
  ActionListener gameButtonListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent event) {
      if (MakeFitnessFunction.getFocos(tablero) == 0) {
        boolean found = false;
        int x = 0;
        int y = 0;
        //guardo el numero que se le quiere poner a la casilla presionada
        int nro = (Integer) spinner.getValue();
        for (int i = 0; i < buttons.length && !found; i++) {
          for (int j = 0; j < buttons[0].length && !found; j++) {
            found = event.getSource().equals(buttons[i][j]);
            if (found) { //si encontre el que fue apretado
              //guardo la posicion
              x = i;
              y = j;
            }
          }
        }
        if (found) {
          boolean b = GeneticLightUp.cargarCasillaNegra(x,y,nro,tablero);
          if (!b) { //si no pudo poner las casilla tiro un mensaje
            JOptionPane.showMessageDialog(null, "Es imposible generar un tablero con esa casilla");
          }
          refreshWindow();
        }
      } else {
        JOptionPane.showMessageDialog(null, "Debe iniciar un nuevo juego");
      }
    }
  }; //endMethod

    
  /**
   * Refresca la pantalla con los cambios en el tablero "buttons".
  */
  public void refreshWindow() {
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[0].length; j++) {
        int aux = tablero[i][j].getSnd();
        if (tablero[i][j].getFst()) { //si es una casilla blanca
          if (aux == 1) { //si tiene foco
            icon = new ImageIcon("src/imagenes/foco.png");
            //acomdo la imagen 
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala); //al boton le pongo la imagen del foco
          } else { //si no tiene foco
            if (MakeFitnessFunction.estaIluminada(i,j,tablero)) { //si esta iluminado
              icon = new ImageIcon("src/imagenes/iluminada.png");
              //Acomodo la imagen
              ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                  50, 50, java.awt.Image.SCALE_DEFAULT));
              buttons[i][j].setIcon(iconoEscala); //le pongo imagen de iluminado
            } else { //si no esta iluminado
              icon = new ImageIcon();
              buttons[i][j].setIcon(icon);
            }
          }
        } else { //si es una casilla negra
          if (aux == -1) { //si tiene -1 es porque no tiene restricciones en cuando a focos
            icon = new ImageIcon("src/imagenes/nada.png");
            //Acomodo imagen
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala); //pongo al boton una casilla negra sin numero
          } else if (aux == 0) { //si tiene 0
            icon = new ImageIcon("src/imagenes/cero.png");
            //acomodo imagen
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala); //pongo imagen de casilla negra con 0
          } else if (aux == 1) { //si tiene 1
            icon = new ImageIcon("src/imagenes/uno.png");
            //acomodo imagen
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala); //le ongo al boton imagen de casilla negra con 1
          } else if (aux == 2) { //si tiene 2
            icon = new ImageIcon("src/imagenes/dos.png");
            //acomodo imagen
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala); //pongo imagen de casilla negra con2
          } else if (aux == 3) { //si tiene 3
            icon = new ImageIcon("src/imagenes/tres.png");
            //acomodo imagen
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                 50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala); //pongo imagen de casilla negra con 3
          } else if (aux == 4) { //si tiene 4
            icon = new ImageIcon("src/imagenes/cuatro.png");
            //acomodo imagen
            ImageIcon iconoEscala = new ImageIcon(icon.getImage().getScaledInstance(
                50, 50, java.awt.Image.SCALE_DEFAULT));
            buttons[i][j].setIcon(iconoEscala);//pongo imagen de casilla negra con 4
          }
        }
      }
    }
  }

}
