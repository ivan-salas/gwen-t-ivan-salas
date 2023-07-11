package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController

/**
 * PlayRound representa el estado para iniciar una ronda.
 * Sirve para demarcar entre el inicio del juego per se y la posterior eleccion de cartas y  turnos de los jugadores
 * @param context: [[GameController]] controlador del juego
 */
class PlayRound(context: GameController) extends GameState(context) {
  // Este estado demarca el inicio de una ronda
  override def GameBegins(): Unit = {
    context.state = new CardsShufNPick(context)
  }

}
