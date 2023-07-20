package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController

class GameEnds(context: GameController) extends GameState(context) {

  override def doAction(): Unit = {
    println("Termino el juego")
  }

}
