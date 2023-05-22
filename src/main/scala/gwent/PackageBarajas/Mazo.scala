package cl.uchile.dcc
package gwent.PackageBarajas

import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}

import java.util.Objects
import scala.collection.mutable.ArrayBuffer

//DEFINICION DE LA CLASE MAZO:
//MAZO SON LAS MAXIMO 25 CARTAS DE CUALQUIER CLASIFICACION DE LAS CUALES EL JUGADOR PUEDE ROBAR PARA TENER EN SU MANO
class Mazo(CartasMembers: ArrayBuffer[AbstractCarta]) extends AbstractBaraja(CartasMembers) {

  //canEqual cumple la misma funcion de antes
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Mazo]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[Mazo]
      (this eq other) || (CartasMembers == other.CartasMembers)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[Mazo], CartasMembers)
  }

  // addMember añade una carta al mazo del jugador
  def addMember(member: AbstractCarta): Unit = {
    CartasMembers.addOne(member)
  }

  // reomveMember quita una carta especifica del mazo del jugador
  def removeMember(member: AbstractCarta): Unit = {
    CartasMembers.remove(CartasMembers.indexOf(member))
  }

  //obtenerCarta entrega una carta del mazo
  //al entregar la carta, esta se quita del mazo
  //consideramos siempre quitar la primera carta, asumiendo que el mazo esta dado vuelta y uno va sacando de arriba
  def obtenerCarta(): AbstractCarta = {
    val carta = CartasMembers.head
    CartasMembers.remove(0)
    carta
  }
}
