package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores.SubZonas

import gwent.PackageCartas.Carta

import scala.collection.mutable.ArrayBuffer
/**
 * Clase que representa una fila de cartas cuerpo a cuerpo en una zona de jugadores del tablero de juego.
 *
 * @param CartasFila El conjunto de cartas en la fila cuerpo a cuerpo.
 */
class FilaCuerpoCuerpo(CartasFila: ArrayBuffer[Carta])
  extends AbstractFila(CartasFila) {

}
