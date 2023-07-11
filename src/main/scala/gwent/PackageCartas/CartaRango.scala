package cl.uchile.dcc
package gwent.PackageCartas

import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import java.util.Objects

/**
 * Clase que representa una carta de rango
 * @param nombre Nombre de la carta
 * @param fuerza Fuerza de la carta
 */
class CartaRango(nombre: String, val fuerza: Int) extends AbstractCarta(nombre){
  //De nuevo se definen canEqual y equals de la misma forma
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[CartaRango]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[CartaRango]
      (this eq other) || (nombre == other.nombre && fuerza == other.fuerza)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[CartaRango], nombre)
  }

  override def JugarEnZona(zona: AbstractZonaPlayers, carta: Carta,tablero: Tablero): Unit = {
    zona.jugarCartaRango(this)
  }

  override def sumarFuerza(): Int = {
    this.fuerza
  }

}
