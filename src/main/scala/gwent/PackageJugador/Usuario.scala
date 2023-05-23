package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageCartas.{AbstractCarta, Carta}

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageTablero.Tablero

import java.util.Objects

//DEFINICION DE CLASE: Usuario
class Usuario(val nombre: String, gemas: Int, mazo: Mazo, mano: Mano) extends AbstractJugador(gemas,mazo,mano) {

  // de nuevo se define canEqual,equals y hashcode
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Usuario]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[Usuario]
      (this eq other) || (nombre==other.nombre && gemas == other.gemas)
    } else {false}}

  override def hashCode(): Int = {
    Objects.hash(classOf[Usuario], gemas)}
  def ObtenerNumeroCartasMazo(): Int = {
    mazo.CartasMembers.length /*Tambien se definen los mismos metodos para obtener el numero de cartas en el mazo y la mano*/ }
  def ObtenerNumeroCartasMano(): Int = {
    mano.CartasMembers.length}

  
  def jugarCarta(tablero: Tablero, carta: AbstractCarta): Unit = {
    carta.JugarEnZona(tablero.zonaUsuario, carta, tablero)
  }
}
