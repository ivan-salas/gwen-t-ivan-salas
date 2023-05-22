package cl.uchile.dcc
package gwent.PackageCartas

import gwent.PackageTablero.ZonasJugadores.AbstractZonaPlayers

import cl.uchile.dcc.gwent.PackageTablero.Tablero

class CartaAsedio(nombre: String, val fuerza: Int) extends AbstractCarta(nombre){
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

  override def JugarEnZona(zona: AbstractZonaPlayers, carta: Carta,tablero: Tablero): Unit = {
    zona.jugarCartaAsedio(carta)
  }

}
