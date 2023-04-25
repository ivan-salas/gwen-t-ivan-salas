package cl.uchile.dcc
package TestCarta
import gwent.PackageCartas.CartaClima
import munit.FunSuite

//CLASE DE LOS TEST PARA CARTA CLIMA
class CartaClimaTest extends FunSuite {
  var CartaClimaNieve: CartaClima = _
  var CartaClimaLluvia: CartaClima = _
  var CartaClimaNieve_Comparar: CartaClima = _

  override def beforeEach(context: BeforeEach): Unit = {
    CartaClimaNieve = new CartaClima("Nieve")
    CartaClimaNieve_Comparar = new CartaClima("Nieve")
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

  test("Dos cartas con los mismo valores deberian ser iguales") {
    assertEquals(CartaClimaNieve, CartaClimaNieve_Comparar)
    assertEquals(CartaClimaNieve_Comparar, CartaClimaNieve)
    assertNotEquals(CartaClimaNieve, CartaClimaLluvia)
    assertNotEquals(CartaClimaLluvia, CartaClimaNieve)
  }

  test("toString retorna el mismo valor para objetos iguales") {
    assertEquals(CartaClimaNieve.toString(), CartaClimaNieve_Comparar.toString())
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(CartaClimaNieve.hashCode(), CartaClimaNieve_Comparar.hashCode())
  }



  //NOTA AL AYUDANTE: NO SE INCLUYEN MAS TESTS PORQUE LA CLASIFICACION Y EFECTO DE LAS CARTAS NO SE PIDEN IMPLEMENTAR
}


