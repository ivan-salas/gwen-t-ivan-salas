package cl.uchile.dcc
package gwent.PackageBarajas

import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}

import java.util.Objects
import scala.collection.mutable.ArrayBuffer
import scala.util.Random


/**
 * Clase que representa la mano de un jugador
 * @param CartasMembers es un ArrayBuffer que contiene las cartas que tiene el jugador en su mano
 */

class Mano(CartasMembers: ArrayBuffer[AbstractCarta]) extends AbstractBaraja(CartasMembers) {

  /** metodo para obtener el ArrayBuffer de la mano*/
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
  def removeMember(member: Carta): Unit = {
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

  /** sumaFuerza() retorna el valor de la fuerza de las cartas en la mano*/
  def sumaFuerza(): Int = {
    var suma = 0
    for (carta <- CartasMembers) {
      suma += carta.sumarFuerza()
    }
    suma
  }

  /** obtenerCartaClimaAzar() retorna una carta clima al azar de la mano
   * Si no hay una carta clima en la mano, se retorna una carta al azar de la mano
   * */
  def obtenerCartaClimaAzar(): Carta = {
    val cartasClima = CartasMembers.collect { case carta: CartaClima => carta }
    if (cartasClima.nonEmpty) {
      val carta = cartasClima(Random.nextInt(cartasClima.length))
      CartasMembers.remove(CartasMembers.indexOf(carta)) // Eliminamos la carta de la mano
      carta
    } 
    else { // Si no hay cartas clima, retornamos cualquier carta al azar de la mano
      val carta = CartasMembers(scala.util.Random.nextInt(CartasMembers.length))
      CartasMembers.remove(CartasMembers.indexOf(carta)) // Eliminamos la carta de la mano
      carta
    }
  }

}
