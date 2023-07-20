package cl.uchile.dcc
package gwent.PackageTablero.ZonaClima

import gwent.PackageCartas.{Carta, CartaClima}

import cl.uchile.dcc.gwent.PackageTablero.Tablero
/**
 * Clase que representa la zona de la carta de clima en el tablero de juego.
 * @param cartaClima La carta de clima actual en la zona de clima.
 */
class ZonaClima(var cartaClima: Option[CartaClima]) {

  /**
   * Permite jugar una nueva carta de clima en la zona de clima.
   *
   * @param NewCartaClima La nueva carta de clima a jugar (reemplaza la antigua).
   */
  def jugarCartaClima(NewCartaClima: CartaClima,tablero: Tablero): Unit={
    NewCartaClima.nombre match {
      case "Escarcha Mordiente" =>
        // 1. Establece el valor de fuerza de todas las cartas de combate cuerpo a cuerpo en 1.
        tablero.zonaCpu.filaCuerpoCuerpo.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = -cartaFila.fuerza + 1)
        tablero.zonaUsuario.filaCuerpoCuerpo.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = -cartaFila.fuerza + 1)

      case "Niebla Impenetrable" =>
        // 2. Establece el valor de fuerza de todas las cartas de combate a distancia a 1.
        tablero.zonaCpu.filaRango.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = -cartaFila.fuerza + 1)
        tablero.zonaUsuario.filaRango.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = -cartaFila.fuerza + 1)

      case "Lluvia Torrencial" =>
        // 3. Establece el valor de fuerza todas las cartas de asedio a 1.
        tablero.zonaCpu.filaAsedio.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = -cartaFila.fuerza + 1)
        tablero.zonaUsuario.filaAsedio.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = -cartaFila.fuerza + 1)

      case "Clima Despejado" =>
        // 4. Elimina todos los efectos climáticos actualmente en efecto en el campo de batalla.
        tablero.zonaCpu.filaCuerpoCuerpo.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = 0)
        tablero.zonaCpu.filaRango.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = 0)
        tablero.zonaCpu.filaAsedio.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = 0)
        tablero.zonaUsuario.filaCuerpoCuerpo.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = 0)
        tablero.zonaUsuario.filaRango.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = 0)
        tablero.zonaUsuario.filaAsedio.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp = 0)

      case _ =>
    }
    this.cartaClima = Some(NewCartaClima)
  }
  
  /**Metodo para limpiar una zona del tablero*/
  def CleanZona(): Unit ={
    this.cartaClima = None
  }

  /**Metodo para obtener el numero de cartas en una zona del tablero*/
  def NumCartasZona(): Int ={
    if (this.cartaClima.isDefined) 1
    else 0
  }



}
