package cl.uchile.dcc
package gwent.PackageBarajas

import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}

import java.util.Objects
import scala.collection.mutable.ArrayBuffer


/**
 * Clase que representa un mazo de cartas.
 * @param CartasMembers ArrayBuffer de cartas que componen la baraja (son maximo 25 cartas de cualquier clasificación).
 */
class Mazo(CartasMembers: ArrayBuffer[AbstractCarta]) extends AbstractBaraja(CartasMembers) {
  
  def getCartasMazo: ArrayBuffer[AbstractCarta] = {//getter de las cartas del mazo deben ser maximo 25
    if (CartasMembers.length > 25) {
      throw new IllegalArgumentException("El mazo no puede tener mas de 25 cartas")
    }
    else CartasMembers
  }
  

  //canEqual cumple la misma funcion de antes
  override def canEqual(that: Any): Boolean = {
    that.isInstanceOf[Mazo]
  }

  override def equals(that: Any): Boolean = {
    if (canEqual(that)) {
      val other = that.asInstanceOf[Mazo]
      (this eq other) || (CartasMembers == other.getCartasMazo)
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

  /**
   * Funcion que entrega una carta del mazo.
   * al entregar la carta, esta se quita del mazo.
   * Por simplicidad se considera quitar siempre la primera carta mazo.
   * @return AbstractCarta que es la carta que se entrega.
   */
  def obtenerCarta(): AbstractCarta = {
    val carta = CartasMembers.head
    CartasMembers.remove(0)
    carta
  }
  
  def setearMazo(mazo: Mazo): Unit = {
    CartasMembers.clear()
    CartasMembers.addAll(mazo.getCartasMazo)
  }
  
}
