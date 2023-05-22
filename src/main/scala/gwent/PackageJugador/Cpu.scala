package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageCartas.{AbstractCarta, Carta}

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageTablero.Tablero

import java.util.Objects

//DEFINICION DE CLASE: Cpu
//Existen dos tipos de jugadores en el juego
//Cpu y usuario
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
    mazo.CartasMembers.length
  }

  def ObtenerNumeroCartasMano(): Int = {
    mano.CartasMembers.length
  }

  def jugarCarta(tablero: Tablero, carta: AbstractCarta): Unit = {
    carta.JugarEnZona(tablero.zonaCpu,carta,tablero)
  }


}
