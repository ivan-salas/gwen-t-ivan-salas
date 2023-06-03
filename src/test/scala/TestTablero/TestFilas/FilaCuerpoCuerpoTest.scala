package cl.uchile.dcc
package TestTablero.TestFilas

import munit.FunSuite
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.SubZonas.FilaCuerpoCuerpo
import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta, CartaAsedio, CartaClima, CartaCuerpoCuerpo}

import scala.collection.mutable.ArrayBuffer

class FilaCuerpoCuerpoTest extends FunSuite {
  var FilaCuerpoCuerpo1: FilaCuerpoCuerpo = _
  var FilaCuerpoCuerpo2: FilaCuerpoCuerpo = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: Carta = new CartaCuerpoCuerpo("Hanuman", 50)
    val carta2: Carta = new CartaCuerpoCuerpo("Pikachu", 40)
    val carta3: Carta = new CartaCuerpoCuerpo("Charizard", 30)
    val carta4: Carta = new CartaCuerpoCuerpo("Bulbasaur", 20)

    val cartas1 = ArrayBuffer[Carta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartas2 = ArrayBuffer[Carta](carta3, carta4)

    FilaCuerpoCuerpo1 = new FilaCuerpoCuerpo(CartasFila = cartas1)
    FilaCuerpoCuerpo2 = new FilaCuerpoCuerpo(CartasFila = cartas2)

  }

  //Primer Test, una fila cuerpo cuerpo solo debe contener cartas de cuerpo cuerpo
  test("Una fila cuerpo cuerpo debe contener cartas cuerpo cuerpo") {
    //asumiendo para efectos de este test que la fila no está vacia
    val carta1: Carta = new CartaCuerpoCuerpo("Hanuman", 50)
    val carta_clima: Carta = new CartaClima("Lluvia")
    assert(FilaCuerpoCuerpo1.CartasFila.head.getClass == carta1.getClass)
    assert(FilaCuerpoCuerpo2.CartasFila.head.getClass == carta1.getClass)
    assertNotEquals(FilaCuerpoCuerpo1.CartasFila.head.getClass, carta_clima.getClass, clue = ' ')
  }
}