package cl.uchile.dcc
package TestTablero.TestFilas

import gwent.PackageCartas.{Carta, CartaClima, CartaCuerpoCuerpo,CartaRango}
import gwent.PackageTablero.ZonasJugadores.SubZonas.FilaRango

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class FilaRangoTest extends FunSuite {
  var FilaRango1: FilaRango = _
  var FilaRango2: FilaRango = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: Carta = new CartaRango("Hanuman", 50,0)
    val carta2: Carta = new CartaRango("Pikachu", 40,0)
    val carta3: Carta = new CartaRango("Charizard", 30,0)
    val carta4: Carta = new CartaRango("Bulbasaur", 20,0)

    val cartas1 = ArrayBuffer[Carta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartas2 = ArrayBuffer[Carta](carta3, carta4)

    FilaRango1 = new FilaRango(CartasFila = cartas1)
    FilaRango2 = new FilaRango(CartasFila = cartas2)

  }

  //Primer Test, una fila rango solo debe contener cartas de rango
  test("Una fila rango debe contener cartas rango") {
    //asumiendo para efectos de este test que la fila no está vacia
    val carta1: Carta = new CartaRango("Hanuman", 50,0)
    val carta_clima: Carta = new CartaClima("Lluvia")
    assert(FilaRango1.CartasFila.head.getClass == carta1.getClass)
    assert(FilaRango2.CartasFila.head.getClass == carta1.getClass)
    assertNotEquals(FilaRango1.CartasFila.head.getClass, carta_clima.getClass, clue = ' ')
  }
}
