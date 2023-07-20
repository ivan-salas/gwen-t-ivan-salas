package cl.uchile.dcc
package TestCarta

import cl.uchile.dcc.gwent.PackageCartas.CartaRango
import munit.FunSuite

class CartaRangoTest extends FunSuite {
    var cartaRango1: CartaRango = _
    var cartaRango1_Comparar: CartaRango = _
    var cartaRango2: CartaRango = _

    override def beforeEach(context: BeforeEach): Unit = {
      cartaRango1 = new CartaRango("Ranger", fuerza = 10,0)
      cartaRango1_Comparar = new CartaRango("Ranger", fuerza = 10,0)
      cartaRango2 = new CartaRango("Pikachu", fuerza = 20,0)
    }

    // PRIMEROS TESTS: CASO NORMAL Y BORDE PARA EL NOMBRE DE UNA CARTA RANGO
    test("Una carta debe tener el nombre") {
      assertEquals(cartaRango1.nombre, "Ranger")
      assertEquals(cartaRango2.nombre, "Pikachu")
    }

    test("Un carta no puede tener un nombre vacio") {
      assertNotEquals(cartaRango1.nombre, "", clue = "")
      assertNotEquals(cartaRango2.nombre, "", clue = "")
    }

    // SEGUNDOS TESTS: CASO NORMAL Y BORDE PARA LA FUERZA DE UNA CARTA RANGO
    test("Una carta debe tener un valor de fuerza") {
      assertEquals(cartaRango1.fuerza, 10)
      assertEquals(cartaRango2.fuerza, 20)
    }

    test("Un carta no puede tener un valor nulo de fuerza") {
      assertNotEquals(cartaRango1.fuerza, 0, clue = "")
      assertNotEquals(cartaRango2.fuerza, 0, clue = "")
    }

    test("dos cartas con los mismo valores deberian ser iguales") {
      assertEquals(cartaRango1, cartaRango1_Comparar)
      assertEquals(cartaRango1_Comparar, cartaRango1)
      assertNotEquals(cartaRango1, cartaRango2)
      assertNotEquals(cartaRango2, cartaRango1)
    }

    test("toString retorna el mismo valor para objetos iguales") {
      assertEquals(cartaRango1.toString(), cartaRango1_Comparar.toString())
    }

    test("hashCode() retorna el mismo valor para objetos iguales") {
      assertEquals(cartaRango1.hashCode(), cartaRango1_Comparar.hashCode())
    }


}
