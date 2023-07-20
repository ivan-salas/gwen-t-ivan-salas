package cl.uchile.dcc
package gwent.PackageCartas

import gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import cl.uchile.dcc.gwent.PackageTablero.Tablero


/** Trait que define una carta.
 */
trait Carta(val nombre: String,val fuerza: Int, var fuerzaTemp: Int) {
  def sumarFuerza(): Int = {
    this.fuerza + this.fuerzaTemp
  }
  
  /** Metodo para jugar una carta en la zona del usuario*/
  def JugarEnZonaUsuario(carta: Carta, tablero: Tablero): Unit = {}

  /** Metodo para jugar una carta en la zona de la cpu */
  def JugarEnZonaCpu(carta: Carta, tablero: Tablero): Unit = {}

}
