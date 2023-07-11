package cl.uchile.dcc
package gwent.PackageTablero.ZonaClima

import gwent.PackageCartas.{Carta, CartaClima}
/**
 * Clase que representa la zona de la carta de clima en el tablero de juego.
 * @param cartaClima La carta de clima actual en la zona de clima.
 */
class ZonaClima(var cartaClima: CartaClima) {

  /**
   * Permite jugar una nueva carta de clima en la zona de clima.
   *
   * @param NewCartaClima La nueva carta de clima a jugar (reemplaza la antigua).
   */
  def jugarCartaClima(NewCartaClima: CartaClima): Unit={
    this.cartaClima = NewCartaClima
  }

}
