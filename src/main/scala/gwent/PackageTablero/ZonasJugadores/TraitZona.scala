package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores

import gwent.PackageCartas.Carta


/**
 * Trait que representa una zona en el tablero de juego.
 */
trait TraitZona {
  /**
   * Permite jugar una carta cuerpo a cuerpo en la zona de jugadores.
   *
   * @param carta La carta cuerpo a cuerpo a jugar.
   */
  def jugarCartaCuerpoCuerpo(carta: Carta): Unit = {}

  /**
   * Permite jugar una carta de asedio en la zona de jugadores.
   *
   * @param carta La carta de asedio a jugar.
   */
  def jugarCartaAsedio(carta: Carta): Unit = {}

  /**
   * Permite jugar una carta de rango en la zona de jugadores.
   *
   * @param carta La carta de rango a jugar.
   */
  def jugarCartaRango(carta: Carta): Unit = {}

}
