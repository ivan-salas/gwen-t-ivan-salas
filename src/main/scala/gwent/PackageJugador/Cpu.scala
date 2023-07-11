package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageCartas.{AbstractCarta, Carta}

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageTablero.Tablero

import java.util.Objects

/**
 * Clase que representa a la CPU del juego (Existen 2 tipos de jugadores CPU y Usuario)
 * @param gemas Cantidad de gemas que posee la CPU
 * @param mazo Mazo de la CPU
 * @param mano Mano de la CPU
 */
class Cpu(gemas: Int,mazo: Mazo,mano: Mano) extends AbstractJugador(gemas,mazo,mano) {

  //De nuevo se definen canEqual, equals y hascode
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Cpu]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      super.equals(that)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[Cpu], gemas)
  }

  //Tambien se hace una funcion para obtener el numero de cartas del mazo y de la mano
  def ObtenerNumeroCartasMazo(): Int = {
    mazo.getCartasMazo.length
  }

  def ObtenerNumeroCartasMano(): Int = {
    mano.getCartasMano.length
  }

  /**
   * Funcion que permite a la CPU jugar una carta en el tablero en su zona asignada.
   * Es decir que, la carta se jugara en la zona de la CPU.
   * @param tablero Tablero en el que se esta jugando
   * @param carta Carta que se quiere jugar
   */
  def jugarCarta(tablero: Tablero, carta: AbstractCarta): Unit = {
    carta.JugarEnZona(tablero.zonaCpu,carta,tablero)
  }
  



}
