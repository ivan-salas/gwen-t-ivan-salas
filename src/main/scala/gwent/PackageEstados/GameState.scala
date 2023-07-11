package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController

/** Represents a state in the game, as a part of the State Design Pattern implementation.
 *
 * This class is a complete, yet fundamental representation of a game state.
 * It provides a default behaviour for a state within the game, and should be extended
 * by other more specific states.
 * The `context` parameter is used to transition between states.
 *
 * The constructor is protected, which prevents direct instantiation of this class, but allows
 * its use as a base class for other game states.
 *
 * @param context The [[GameController]] which controls state transitions.
 * @constructor Creates a new game state with a given context.
 *
 */
class GameState protected(val context: GameController){
  //Setea el estado del context a este estado
  context.state = this

  /** Transition the game state to [[PlayRound]].
   *
   * This method should be overridden by subclasses that allow this transition.
   *
   * @throws InvalidTransitionException when an invalid state transition is attempted.
   */
  def StartARound(): Unit = {
    transitionError(targetState = "StartARound")
  }

  /** Transition the game state to [[CardsShufNPick]].
   *
   * This method should be overridden by subclasses that allow this transition.
   *
   * @throws InvalidTransitionException when an invalid state transition is attempted.
   */
  def GameBegins(): Unit = {
    transitionError(targetState = "GameBegins")
  }

  /** Transition the game state to [[UserTurn]].
   *
   * This method should be overridden by subclasses that allow this transition.
   *
   * @throws InvalidTransitionException when an invalid state transition is attempted.
   */
  def TurnOfUSer(): Unit = {
    transitionError(targetState = "TurnOfUser")
  }

  /** Transition the game state to [[UserTurn]] or [[CPUTurn]].
   *
   * This method should be overridden by subclasses that allow this transition.
   *
   * @throws InvalidTransitionException when an invalid state transition is attempted.
   */
  def UserPassTurn(): Unit = {
    transitionError(targetState = "PassTurn")
  }

  def UserPlayOneCard(): Unit = {
    transitionError(targetState = "PlayOneCard")
  }

  def UserPlayCardAgain(): Unit = {
    transitionError(targetState = "PlayCardAgain")
  }

  def CpuPassTurn(): Unit = {
    transitionError(targetState = "PassTurn")
  }

  def CpuPlayOneCard(): Unit = {
    transitionError(targetState = "PlayOneCard")
  }

  def CpuPlayCardAgain(): Unit = {
    transitionError(targetState = "PlayCardAgain")
  }

  def PlayNextRound(): Unit = {
    transitionError(targetState = "PlayNextRound")
  }

  def OutOfGems(): Unit = {
    transitionError(targetState = "OutOfGems")
  }

  def doAction(): Unit = {
    // do nothing
  }


  /** Throws an [[InvalidTransitionException]] with a message about an invalid transition.
   *
   * Notice that this uses [[getClass]] to print the name of the current class.
   * This is one of the few use cases of [[getClass]] that is not considered bad practice, since it
   * is used to print the name of the current class, and not to check the type of an object.
   *
   * @param targetState The name of the state that was attempted to transition to.
   * @throws InvalidTransitionException always.
   */
  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }


}
