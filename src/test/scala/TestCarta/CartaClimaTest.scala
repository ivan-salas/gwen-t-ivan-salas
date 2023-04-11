package cl.uchile.dcc
package TestCarta
import gwent.PackageCartas.CartaClima
import munit.FunSuite

class CartaClimaTest extends FunSuite {
  var CartaClimaNieve: CartaClima = _
  var CartaClimaLluvia: CartaClima = _

  override def beforeEach(context: BeforeEach): Unit = {
    CartaClimaNieve = new CartaClima("Nieve")
    CartaClimaLluvia = new CartaClima("Lluvia")
  }

  // PRIMEROS TEST: CASO NORMAL Y BORDE PARA EL NOMBRE DE UNA CARTA CLIMA
  test("Una carta debe tener el nombre") {
    assertEquals(CartaClimaNieve.nombre, "Nieve")
    assertEquals(CartaClimaLluvia.nombre, "Lluvia")
  }

  test("Un carta no puede tener un nombre vacio") {
    assertNotEquals(CartaClimaNieve.nombre,"","Clue?")
  }

  //NOTA AL AYUDANTE: NO SE INCLUYEN MAS TESTS PORQUE LA CLASIFICACION Y EFECTO DE LAS CARTAS NO SE PIDEN IMPLEMENTAR
}

