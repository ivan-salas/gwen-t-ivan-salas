package cl.uchile.dcc
package TestCarta
import gwent.PackageCartas.CartaCuerpoCuerpo
import munit.FunSuite

//CLASE DE TEST PARA CARTAUNIDAD
class CartaCuerpoCuerpoTest extends FunSuite {
  var cartaUnidad1: CartaCuerpoCuerpo = _
  var cartaUnidad1_Comparar: CartaCuerpoCuerpo = _
  var cartaUnidad2: CartaCuerpoCuerpo = _

  override def beforeEach(context: BeforeEach): Unit = {
    cartaUnidad1 = new CartaCuerpoCuerpo("Hanuman", fuerza = 10)
    cartaUnidad1_Comparar = new CartaCuerpoCuerpo("Hanuman", fuerza = 10)
    cartaUnidad2 = new CartaCuerpoCuerpo("Thor",fuerza = 20)
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

  test("dos cartas con los mismo valores deberian ser iguales") {
    assertEquals(cartaUnidad1, cartaUnidad1_Comparar)
    assertEquals(cartaUnidad1_Comparar, cartaUnidad1)
    assertNotEquals(cartaUnidad1, cartaUnidad2)
    assertNotEquals(cartaUnidad2, cartaUnidad1)
  }

  test("toString retorna el mismo valor para objetos iguales") {
    assertEquals(cartaUnidad1.toString(), cartaUnidad1_Comparar.toString())
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(cartaUnidad1.hashCode(), cartaUnidad1_Comparar.hashCode())
  }
  
}
