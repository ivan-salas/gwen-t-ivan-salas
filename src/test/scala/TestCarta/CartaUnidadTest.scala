package cl.uchile.dcc
package TestCarta
import gwent.PackageCartas.CartaUnidad
import munit.FunSuite

class CartaUnidadTest extends FunSuite {
  var cartaUnidad1: CartaUnidad = _
  var cartaUnidad2: CartaUnidad = _

  override def beforeEach(context: BeforeEach): Unit = {
    cartaUnidad1 = new CartaUnidad("Hanuman", fuerza = 10)
    cartaUnidad2 = new CartaUnidad("Thor",fuerza = 20)
  }

  // PRIMEROS TESTS: CASO NORMAL Y BORDE PARA EL NOMBRE DE UNA CARTA DE UNIDAD
  test("Una carta debe tener el nombre") {
    assertEquals(cartaUnidad1.nombre, "Hanuman")
    assertEquals(cartaUnidad2.nombre, "Thor")
  }

  test("Un carta no puede tener un nombre vacio") {
    assertNotEquals(cartaUnidad1.nombre,"",clue="")
    assertNotEquals(cartaUnidad2.nombre,"",clue="")
  }

  // SEGUNDOS TESTS: CASO NORMAL Y BORDE PARA LA FUERZA DE UNA CARTA DE UNIDAD
  test("Una carta debe tener un valor de fuerza") {
    assertEquals(cartaUnidad1.fuerza, 10)
    assertEquals(cartaUnidad2.fuerza, 20)
  }

  test("Un carta no puede tener un valor nulo de fuerza") {
    assertNotEquals(cartaUnidad1.fuerza,0,clue="")
    assertNotEquals(cartaUnidad2.fuerza,0,clue="")


  }

  //NOTA AL AYUDANTE: NO SE INCLUYEN MAS TESTS PORQUE LA CLASIFICACION Y EFECTO DE LAS CARTAS NO SE PIDEN IMPLEMENTAR
}
