package cl.uchile.dcc
import munit.FunSuite
import gwent.CartaClima

class CartaClimaTest extends FunSuite {
  var CartaClimaNieve: CartaClima = _
  var CartaClimaLluvia: CartaClima = _

  override def beforeEach(context: BeforeEach): Unit = {
    CartaClimaNieve = new CartaClima("Nieve", "USUARIO")
    CartaClimaLluvia = new CartaClima("Lluvia", "CPU")
  }

  test("Una carta debe tener el nombre") {
    assertEquals(CartaClimaNieve.nombre, "Nieve")
    assertEquals(CartaClimaLluvia.nombre, "Lluvia")
  }



}

