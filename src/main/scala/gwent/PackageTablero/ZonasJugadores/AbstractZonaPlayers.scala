package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores

import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}

import cl.uchile.dcc.gwent.PackageCartas.{Carta, CartaCuerpoCuerpo}

import scala.language.postfixOps
/**
 * Clase abstracta que representa una zona de jugadores en el tablero de juego.
 *
 * @param filaRango        La fila de cartas de rango en la zona de jugadores.
 * @param filaCuerpoCuerpo La fila de cartas cuerpo a cuerpo en la zona de jugadores.
 * @param filaAsedio       La fila de cartas de asedio en la zona de jugadores.
 */
abstract class AbstractZonaPlayers(val filaRango: FilaRango,val filaCuerpoCuerpo: FilaCuerpoCuerpo, val filaAsedio: FilaAsedio )
  extends TraitZona {

  /**Metodo para devolver la fuerza acumulada de cartas en una zona*/
  def sumaFuerza(): Int = {
    filaRango.sumaFuerza() + filaCuerpoCuerpo.sumaFuerza() + filaAsedio.sumaFuerza()
  }

  /**Metodo para limpiar una zona del tablero*/
  def CleanZona(): Unit = {
    this.filaRango.CleanFila()
    this.filaCuerpoCuerpo.CleanFila()
    this.filaAsedio.CleanFila()
  }

  /**Metodo para obtener el numero de cartas en una zona del tablero*/
  def NumCartasZona(): Int = {
    this.filaRango.NumCartasFila() + this.filaCuerpoCuerpo.NumCartasFila() + this.filaAsedio.NumCartasFila()
  }

}

