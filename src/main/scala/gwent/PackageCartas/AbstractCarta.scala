package cl.uchile.dcc
package gwent.PackageCartas

import gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import cl.uchile.dcc.gwent.PackageTablero.Tablero


/**
 * Clase abstracta que representa una carta del juego.
 * @param nombre nombre de la carta.
 */
abstract class AbstractCarta(val nombre: String) extends Carta with Equals {
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) { // Preguntamos si el obj que estamos comparando es del mismo tipo de esta
                          // clase. 
      val other = that.asInstanceOf[AbstractCarta] // Si lo es, realizamos un casteo para obtener las propiedades de
                                                  // dicha clase y poder compararlas.
      (this eq other) || (nombre == other.nombre) // retornamos lo que dé este resultado, si es que son iguales
                                                  // referencialmente estamos listos, o que sus parámetros sean iguales.
    } else { // no son iguales.
      false // se retorna falso
    }
  }
  override def toString: String = { // No confundir con getClass.getName,
    // solo considerar que entrega el nombre de la clase.
    s"""Class: ${getClass.getName}
       |Name: $nombre""".stripMargin // stripMargin se coloca automáticamente cuando
    // queremos hacer un String de más de una línea.

  }

  /**
   * Método que permite jugar una carta en una zona de un jugador.
   * @param zona zona en la que se quiere jugar la carta (puede ser ZonaCpu o ZonaUsuario).
   * @param carta carta que se quiere jugar.
   * @param tablero tablero en el que se está jugando.
   */
  def JugarEnZona(zona: AbstractZonaPlayers,carta: Carta, tablero: Tablero): Unit = {
  }

}
