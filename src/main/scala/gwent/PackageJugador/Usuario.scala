package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageCartas.{AbstractCarta, Carta}

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageTablero.Tablero

import java.util.Objects


/**
 * Clase que representa al jugador humano (un usuario).
 * @param nombre nombre de Usuario
 * @param gemas Cantidad de gemas
 * @param mazo Mazo de cartas del usuario
 * @param mano  Mano de cartas del usuario
 */
class Usuario(val nombre: String, gemas: Int, mazo: Mazo, mano: Mano) extends AbstractJugador(gemas,mazo,mano) {

  // de nuevo se define canEqual,equals y hashcode
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Usuario]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[Usuario]
      (this eq other) || (nombre==other.nombre && gemas == other.getGemas && mazo == other.getMazo && mano == other.getMano)
    } else {false}}

  override def hashCode(): Int = {
    Objects.hash(classOf[Usuario], gemas)}
  def ObtenerNumeroCartasMazo(): Int = {
    mazo.getCartasMazo.length /*Tambien se definen los mismos metodos para obtener el numero de cartas en el mazo y la mano*/ }
  def ObtenerNumeroCartasMano(): Int = {
    mano.getCartasMano.length}


  /**
   * Metodo que permite al usuario jugar una carta en su zona asignada en el tablero.
   * Es decir, la carta se juega en la zona del usuario
   * @param tablero tablero donde se va a jugar la cart
   * @param carta carta que se va a jugar
   */
  def jugarCarta(tablero: Tablero, carta: Carta): Unit = {
    carta.JugarEnZonaUsuario(carta, tablero)
  }
}
