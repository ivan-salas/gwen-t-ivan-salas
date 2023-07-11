package cl.uchile.dcc
package gwent.PackageJugador

import gwent.GameController

/** Clase encargada de observar los cambios en las gemas de los jugadores.
 * 
  * Esta clase le notifica al controlador cuando un jugador gana o pierde una gema
  * El controlador se encarga de actualizar el estado del juego a traves del metodo OutOfGems()
  * @param player: [[AbstractJugador]] Jugador que se esta observando
  * @param controlador: [[GameController]] del juego 
 */
class GemObserver(player: AbstractJugador, controlador: GameController) {

  /**
   * Metodo que le notifica al controlador que el jugador se quedo sin gemas
   * 
   */
  def update(): Unit = {
    if (player.gemas == 0) {
      controlador.OutOfGems()
    }
  }
  
}
