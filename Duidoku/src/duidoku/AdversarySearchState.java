package duidoku;

/**
 * Interface which defines the basic requirements on states, needed 
 * when characterising problems as adversary search problems. State 
 * definitions for particular adversary search problems should 
 * implement this interface, so that general adversary search 
 * strategies can be used.
 * @author Nazareno Aguirre
 * @version 0.1, 17/05/2010
 */
public interface AdversarySearchState extends State {

  /** 
   * Indicates whether the current state is a max state or not.
   * If the current state is not a 'max' state, then it is assumed
   * to be a min state. 
   * @return true iff 'this' is a max state.
   * @pre. true.
   * @post. true is returned iff 'this' is a max state.
  */
  abstract boolean isMax();

  /**
   * Cambia el valor de max.
   * @param m : valor a asignarle a max : boolean
   */
  abstract void setMax(boolean m);



  /** 
   * Returns a representation as a string of the current state. 
   * @return a string representing the current state.
   * @pre. true.
   * @post. A text representation of the current state is returned.
  */
  public abstract String toString();

  /** 
   * Returns an object representing the rule applied, leading to the
   * current state. 
   * @return an object representing the rule applied, leading to the
   *     current state. If the state is the initial state, then null is 
   *     returned.
   * @pre. true.
   * @post. An object representing the rule applied, leading to the
   *     current state, is returned. If the state is the initial state, 
   *     then null is returned.
   *     TODO Replace Object by a more specific class or interface.
  */
  abstract Object ruleApplied();
  
}

