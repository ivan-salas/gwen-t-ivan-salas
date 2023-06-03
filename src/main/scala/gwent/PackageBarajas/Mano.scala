package cl.uchile.dcc
package gwent.PackageBarajas

import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}

import java.util.Objects
import scala.collection.mutable.ArrayBuffer


/**
 * Clase que representa la mano de un jugador
 * @param CartasMembers es un ArrayBuffer que contiene las cartas que tiene el jugador en su mano
 */

class Mano(CartasMembers: ArrayBuffer[AbstractCarta]) extends AbstractBaraja(CartasMembers) {

  def getCartasMano: ArrayBuffer[AbstractCarta] = { //getter de las cartas de la mano
    CartasMembers 
  }


  // canEqual cumple la misma funciona que antes
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Mano] //se checkea que son objetos de la misma clase (mano)
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      super.equals(that) //Se llama al equals de la clase abstracta
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[Mano], CartasMembers)
  }

  //addMember añade una carta a la mano del jugador
  def addMember(member: AbstractCarta): Unit = {
    CartasMembers.addOne(member)
  }

  //removeMember quita una carta de la mano del jugador
  def removeMember(member: AbstractCarta): Unit = {
    CartasMembers.remove(CartasMembers.indexOf(member))
  }

  /**
   * Funcion que saca una carta de cierto nombre de la mano del jugador.
   * Al sacar la carta, esta se elimina de la mano
   * @param nombre es el nombre de la carta que se quiere sacar
   * @return  la carta que se saco de la mano
   */
  def sacarCarta(nombre: String): AbstractCarta = {
    val carta = CartasMembers.find(carta => carta.nombre == nombre)
    CartasMembers.remove(CartasMembers.indexOf(carta.get))
    carta.get
  }

}
