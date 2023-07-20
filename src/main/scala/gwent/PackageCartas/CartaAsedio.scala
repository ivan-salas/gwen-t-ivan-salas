package cl.uchile.dcc
package gwent.PackageCartas

import gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import cl.uchile.dcc.gwent.PackageTablero.Tablero

import java.util.Objects

/**
 * Clase que representa una carta de asedio, la cual tiene un nombre y  fuerza.
 * @param nombre nombre de la carta.
 * @param fuerza fuerza de la carta.
 */
class CartaAsedio(nombre: String, fuerza: Int, fuerzaTemp: Int) extends AbstractCarta(nombre, fuerza, fuerzaTemp){
  //De nuevo se definen canEqual y equals de la misma forma
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[CartaAsedio]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[CartaAsedio]
      (this eq other) || (nombre == other.nombre && fuerza == other.fuerza)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[CartaAsedio], nombre)
  }

  override def JugarEnZonaUsuario(carta: Carta,tablero: Tablero): Unit = {
    tablero.zonaUsuario.jugarCartaAsedio(carta)
  }

  override def JugarEnZonaCpu(carta: Carta, tablero: Tablero): Unit = {
    tablero.zonaCpu.jugarCartaAsedio(carta)
  }


}
