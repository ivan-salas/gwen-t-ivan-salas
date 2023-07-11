package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores.SubZonas

import gwent.PackageCartas.Carta
import scala.collection.mutable.ArrayBuffer
/**
 * Clase abstracta que representa una fila de cartas en una zona de jugadores (Cpu o Usuario) del tablero de juego.
 *
 * @param CartasFila El conjunto de cartas en la fila.
 */
class AbstractFila(val CartasFila: ArrayBuffer[Carta]) {
  def sumaFuerza(): Int ={
    var suma = 0
    for (carta <- CartasFila){
      suma += carta.sumarFuerza()
    }
    suma
  }

}
