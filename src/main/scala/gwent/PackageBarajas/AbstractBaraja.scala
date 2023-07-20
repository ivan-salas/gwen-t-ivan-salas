package cl.uchile.dcc
package gwent.PackageBarajas

import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}

import scala.collection.mutable.ArrayBuffer

/**
 * Clase abstracta que define una baraja de cartas.
 * @param CartasMembers ArrayBuffer de cartas que componen la baraja.
 */
abstract class AbstractBaraja( val CartasMembers: ArrayBuffer[AbstractCarta]) extends Baraja with Equals {
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) { // Preguntamos si el obj que estamos comparando es del mismo tipo de esta
                          // clase.
      val other = that.asInstanceOf[AbstractBaraja] // Si lo es, realizamos un casteo para obtener las propiedades de
                                                    // dicha clase y poder compararlas.
      (this eq other) || (CartasMembers == other.CartasMembers) // retornamos lo que dé este resultado, si es que son iguales
                                                                // referencialmente estamos listos, o que sus parámetros sean iguales.
    } else { // no son iguales.
      false // se retorna falso.
    }
  }

  override def toString: String = { // No confundir con getClass.getName,
    // solo considerar que entrega el nombre de la clase.
    s"""Class: ${getClass.getName}
       |Name: $CartasMembers""".stripMargin // stripMargin se coloca automáticamente cuando
    // queremos hacer un String de más de una línea.

  }

  /** Metodo para obtener una carta al azar de la baraja. */
  def obtenerCartaAzar(): AbstractCarta = {
    val carta = CartasMembers(scala.util.Random.nextInt(CartasMembers.length))
    CartasMembers.remove(CartasMembers.indexOf(carta))
    carta
  }
  

}
