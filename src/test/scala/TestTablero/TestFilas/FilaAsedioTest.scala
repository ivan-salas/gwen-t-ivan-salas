package cl.uchile.dcc
package TestTablero.TestFilas

import munit.FunSuite
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.SubZonas.FilaAsedio
import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta, CartaClima, CartaAsedio}
import scala.collection.mutable.ArrayBuffer

class FilaAsedioTest extends FunSuite {
  var FilaAsedio1: FilaAsedio = _
  var FilaAsedio2: FilaAsedio = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: Carta = new CartaAsedio("Hanuman", 50,0)
    val carta2: Carta = new CartaAsedio("Pikachu", 40,0)
    val carta3: Carta = new CartaAsedio("Charizard", 30,0)
    val carta4: Carta = new CartaAsedio("Bulbasaur", 20,0)

    val cartas1 = ArrayBuffer[Carta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartas2 = ArrayBuffer[Carta](carta3, carta4)

    FilaAsedio1 = new FilaAsedio(CartasFila = cartas1)
    FilaAsedio2 = new FilaAsedio(CartasFila = cartas2)

  }

  //Primer Test, una fila de asedio solo debe contener cartas de asedio
  test("Una fila de asedio debe contener cartas de asedio") {
  //asumiendo para efectos de este test que la fila no está vacia
    val carta1: Carta = new CartaAsedio("Hanuman", 50,0)
    val carta_clima: Carta = new CartaClima("Lluvia")
    assert(FilaAsedio1.CartasFila.head.getClass == carta1.getClass)
    assert(FilaAsedio2.CartasFila.head.getClass == carta1.getClass)
    assertNotEquals(FilaAsedio1.CartasFila.head.getClass, carta_clima.getClass,clue = ' ')
  }

}
