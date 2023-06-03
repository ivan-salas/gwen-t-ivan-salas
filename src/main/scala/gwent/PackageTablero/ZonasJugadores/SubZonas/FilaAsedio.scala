package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores.SubZonas

import cl.uchile.dcc.gwent.PackageCartas.Carta

import scala.collection.mutable.ArrayBuffer
/**
 * Clase que representa una fila de cartas de asedio en una zona de jugadores del tablero de juego.
 *
 * @param CartasFila El conjunto de cartas en la fila de asedio.
 */
class FilaAsedio(CartasFila: ArrayBuffer[Carta])
  extends AbstractFila(CartasFila) {

}
