package cl.uchile.dcc
package gwent.PackageCartas
import java.util.Objects

//DEFINICION DE CLASE: CartaClima
//CartaClima es uno de los dos tipos de carta que existen
//Como todavia no se deben realizar los efectos en el tablero
//solo consideramos que CartaClima tiene un nombre unico que la identifica
class CartaClima(nombre: String) extends AbstractCarta(nombre) {

  override def hashCode(): Int = {
    Objects.hash(classOf[CartaClima], nombre)
  }
  
  //de nuevo canEqual y equals hacen lo mismo que anteriormente
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[CartaClima]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      super.equals(that)
    } else {
      false
    }
  }




  }