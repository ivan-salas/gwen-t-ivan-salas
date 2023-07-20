package cl.uchile.dcc
package TestCarta

import gwent.PackageCartas.CartaAsedio

import munit.FunSuite

class CartaAsedioTest extends FunSuite {
  var cartaAsedio1: CartaAsedio = _
  var cartaAsedio1_Comparar: CartaAsedio = _
  var cartaAsedio2: CartaAsedio = _

  override def beforeEach(context: BeforeEach): Unit = {
    cartaAsedio1 = new CartaAsedio("Ranger", fuerza = 10,0)
    cartaAsedio1_Comparar = new CartaAsedio("Ranger", fuerza = 10,0)
    cartaAsedio2 = new CartaAsedio("Pikachu", fuerza = 20,0)
  }

  // PRIMEROS TESTS: CASO NORMAL Y BORDE PARA EL NOMBRE DE UNA CARTA ASEDIO
  test("Una carta debe tener el nombre") {
    assertEquals(cartaAsedio1.nombre, "Ranger")
    assertEquals(cartaAsedio2.nombre, "Pikachu")
  }

  test("Un carta no puede tener un nombre vacio") {
    assertNotEquals(cartaAsedio1.nombre, "", clue = "")
    assertNotEquals(cartaAsedio2.nombre, "", clue = "")
  }

  // SEGUNDOS TESTS: CASO NORMAL Y BORDE PARA LA FUERZA DE UNA CARTA ASEDIO
  test("Una carta debe tener un valor de fuerza") {
    assertEquals(cartaAsedio1.fuerza, 10)
    assertEquals(cartaAsedio2.fuerza, 20)
  }

  test("Un carta no puede tener un valor nulo de fuerza") {
    assertNotEquals(cartaAsedio1.fuerza, 0, clue = "")
    assertNotEquals(cartaAsedio2.fuerza, 0, clue = "")
  }

  test("dos cartas con los mismo valores deberian ser iguales") {
    assertEquals(cartaAsedio1, cartaAsedio1_Comparar)
    assertEquals(cartaAsedio1_Comparar, cartaAsedio1)
    assertNotEquals(cartaAsedio1, cartaAsedio2)
    assertNotEquals(cartaAsedio2, cartaAsedio1)
  }

  test("toString retorna el mismo valor para objetos iguales") {
    assertEquals(cartaAsedio1.toString(), cartaAsedio1_Comparar.toString())
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(cartaAsedio1.hashCode(), cartaAsedio1_Comparar.hashCode())
  }


}
