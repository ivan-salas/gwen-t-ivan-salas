package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageBarajas.{Mano, Mazo}

import cl.uchile.dcc.gwent.PackageCartas.Carta
import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.{AbstractZonaPlayers, TraitZona}

//DEFINICION CLASE ABSTRACTA: AbstractJugador
//La clase AbstractJugador incluye los valores de gemas, su mazo y su mano
abstract class AbstractJugador(var gemas: Int, var mazo: Mazo, var mano: Mano) extends Jugador with Equals{

// De nuevo se definen equals y tostring
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) { // Preguntamos si el obj que estamos comparando es del mismo tipo de esta
      // clase
      val other = that.asInstanceOf[AbstractJugador] // Si lo es, realizamos un casteo para obtener las propiedades de
      // dicha clase y poder compararlas.
      (this eq other) || (gemas==other.gemas) // retornamos lo que dé este resultado, si es que son iguales
      // referencialmente estamos listos, o que sus parámetros sean iguales.
    } else { // Chao, no son iguales.
      false // retornamos falso de una.
    }
  }

  override def toString: String = { // No se confundan con getClass.getName,
    // solo consideren que entrega el nombre de la clase.
    s"""Class: ${getClass.getName}
       |gemas: $gemas""".stripMargin // stripMargin se coloca automáticamente cuando
    // queremos hacer un String de más de una línea.

  }

  def robarCarta(): Unit = {
    mano.addMember(mazo.obtenerCarta()) //Tambien se define la funcion robarCarta, que roba una carta del mazo y la añade a la mano
  }





}
