package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores

import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}

import cl.uchile.dcc.gwent.PackageCartas.Carta
/**
 * Clase abstracta que representa una zona de jugadores en el tablero de juego.
 *
 * @param filaRango        La fila de cartas de rango en la zona de jugadores.
 * @param filaCuerpoCuerpo La fila de cartas cuerpo a cuerpo en la zona de jugadores.
 * @param filaAsedio       La fila de cartas de asedio en la zona de jugadores.
 */
abstract class AbstractZonaPlayers(val filaRango: FilaRango,val filaCuerpoCuerpo: FilaCuerpoCuerpo, val filaAsedio: FilaAsedio )
  extends TraitZona {
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
  def jugarCartaRango(carta: Carta):Unit={}

}

