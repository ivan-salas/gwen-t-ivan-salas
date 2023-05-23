package cl.uchile.dcc
package gwent.PackageCartas

import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import java.util.Objects

//DEFINICION DE CLASE: CartaUnidad
//CartaUnidad es el otro tipo de carta que existe en el juego
//CartaUnidad tambien puede tener un valor de fuerza
//AQUI HUBO UN ERROR, HABIA QUE SUBDIVIDIR LAS CARTAS DE UNIDAD EN TRES TIPOS:
//1.CUERPO A CUERPO
//2.RANGO
//3.ASEDIO
//NUESTRO CODIGO NO CAPTA BIEN ESA DIVISON Y SI SE ASUME QUE EL NOMBRE: STRING GUARDA ESA INFO, ENTONCES EL CODIGO ES FRAGIL
class CartaCuerpoCuerpo(nombre: String, val fuerza: Int) extends AbstractCarta(nombre) {

  //De nuevo se definen canEqual y equals de la misma forma
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[CartaCuerpoCuerpo]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[CartaCuerpoCuerpo]
      (this eq other) || (nombre == other.nombre && fuerza == other.fuerza)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[CartaClima], nombre)
  }

  override def JugarEnZona(zona: AbstractZonaPlayers, carta: Carta,tablero: Tablero): Unit = {
    zona.jugarCartaCuerpoCuerpo(carta)
  }

}
