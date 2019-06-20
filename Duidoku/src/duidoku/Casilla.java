package duidoku;

/**
 * Clase auxiliar para representar el tablero.
 * @author Yanina Celi y Agustin Borda
 *
 */
public class Casilla {

  /**
   * si "fst" es true, representa que esta libre, 
   * y si es false, que ya fue usada. 
  */
  private Boolean fst;
  /**
   * si "fst" es falso, snd representa el numero que
   *  se puso en el tablero en esa casilla.
   */
  private Integer snd;


  //constructores 

  /**
   * constructor por defecto.
  */
  public Casilla() {
    fst = null;
    snd = null;
  }

  /**
   * Constructor a partir de valores.
   * @param f : valor a asignar en fst : Boolean
   * @param s : valor a asignar en snd : Integer
  */
  public Casilla(Boolean f, Integer s) {
    fst = f;
    snd = s;
  }

  //gets

  /**
   * Get del atributo fst.
   * @return valor del atributo fst : Boolean
  */
  public Boolean getFst() {
    return fst;
  }

  /**
   * Get del atributo snd.
   * @return valor del atributo snd : Integer
  */
  public Integer getSnd() {
    return snd;
  }

  //sets

  /**
   * Cambia el valor de fst por f.
   * @param f : valor a asignarle a fst : Boolean
  */
  public void setFst(Boolean f) {
    fst = f;
  }

  /**
   * Cambia el valor de snd por s.
   * @param s : valor a asignarle a snd : Integer
  */
  public void setSnd(Integer s) {
    snd = s;
  }

  /**
   *Compara dos casillas.
   *@param p : casilla con que la se va a comparar : Casilla
   *@return valor de verdad de comparar this con p : boolean
  */
  public boolean equals(Casilla p) {
    boolean e = (p.getFst().compareTo(this.getFst()) == 0);
    return ((e) && (p.getSnd().compareTo(this.getSnd()) == 0));
  }

  /**
   * Retorna una casilla en String.
   * @return casilla en forma de String, con fst y snd : String
  */
  public String toString() {
    String a = "(" + getFst() + "," + getSnd() + ")";
    return a;
  }


}
