package cl.uchile.dcc
package gwent.PackageTablero

import gwent.PackageTablero.ZonaClima.ZonaClima

import cl.uchile.dcc.gwent.PackageCartas.Carta
import cl.uchile.dcc.gwent.PackageJugador.Jugador
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.{ZonaCpu, ZonaUsuario}

/**
 * Clase que representa el tablero de juego.
 *
 * @param zonaClima    La zona de la carta de clima del tablero.
 * @param zonaCpu      La zona de la CPU del tablero.
 * @param zonaUsuario  La zona del usuario del tablero.
 */
class Tablero(val zonaClima: ZonaClima, val zonaCpu: ZonaCpu, val zonaUsuario: ZonaUsuario) {

}

