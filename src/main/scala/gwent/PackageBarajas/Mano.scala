package cl.uchile.dcc
package gwent.PackageBarajas

import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}

import java.util.Objects
import scala.collection.mutable.ArrayBuffer

//DEFINICION DE CLASE: Mano
//MANO ES LAS CARTAS QUE, VALGA LA REDUNDANCIA, TIENE UN JUGADOR EN SU MANO
class Mano(CartasMembers: ArrayBuffer[AbstractCarta]) extends AbstractBaraja(CartasMembers) {


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
}
