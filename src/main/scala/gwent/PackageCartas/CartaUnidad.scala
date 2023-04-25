package cl.uchile.dcc
package gwent.PackageCartas

import java.util.Objects

//DEFINICION DE CLASE: CartaUnidad
//CartaUnidad es el otro tipo de carta que existe en el juego
//CartaUnidad tambien puede tener un valor de fuerza
class CartaUnidad(nombre: String, val fuerza: Int) extends AbstractCarta(nombre) {

  //De nuevo se definen canEqual y equals de la misma forma
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[CartaUnidad]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[CartaUnidad]
      (this eq other) || (nombre == other.nombre && fuerza == other.fuerza)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[CartaClima], nombre)
  }

}
