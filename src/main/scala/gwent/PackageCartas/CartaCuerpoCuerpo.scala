package cl.uchile.dcc
package gwent.PackageCartas

import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import java.util.Objects

/**
 * Clase que representa una carta de tipo Cuerpo a Cuerpo.
 *
 * @param nombre Nombre de la carta.
 * @param fuerza Fuerza de la carta.
 */
class CartaCuerpoCuerpo(nombre: String, fuerza: Int, fuerzaTemp: Int) extends AbstractCarta(nombre,fuerza,fuerzaTemp) {

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
    Objects.hash(classOf[CartaCuerpoCuerpo], nombre)
  }

  override def JugarEnZonaUsuario(carta: Carta,tablero: Tablero): Unit = {
    tablero.zonaUsuario.jugarCartaCuerpoCuerpo(carta)
  }

  override def JugarEnZonaCpu(carta: Carta, tablero: Tablero): Unit = {
    tablero.zonaCpu.jugarCartaCuerpoCuerpo(carta)
  }

}
