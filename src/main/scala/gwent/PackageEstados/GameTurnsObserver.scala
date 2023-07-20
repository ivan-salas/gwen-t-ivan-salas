package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController

/* Esta Clase es un observador de los turnos del juego
* Se encarga de notificar al controlador cuando se han acabado los turnos de una partida
* Puede setearse para contar los turnos de una partida
* */
class GameTurnsObserver(var turnos: Int, controlador: GameController) {
  /** Update() sirve para notificar o no al controlador de que se acabaron los turnos*/
  def update(): Unit = {
    if (turnos == 0) { //Si se acaban los turnos, se notifica al controlador
      controlador.PlayNextRound()
    }
  }
}
