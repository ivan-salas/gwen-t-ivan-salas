package cl.uchile.dcc
package TestBaraja

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta, CartaClima, CartaCuerpoCuerpo}
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

//CLASE DE LOS TEST PARA LA CLASE MANO
class ManoTest extends FunSuite{
  var Mano1: Mano = _
  var Mano1_Comparar: Mano = _
  var Mano2: Mano = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: AbstractCarta = new CartaCuerpoCuerpo("Hanuman", 50)
    val carta2: AbstractCarta = new CartaCuerpoCuerpo("Ganesha", 30)
    val carta3: AbstractCarta = new CartaClima("Lluvia")
    val cartasMano1 = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartasMano2 = ArrayBuffer[AbstractCarta](carta1, carta2, carta3)

    Mano1 = new Mano(cartasMano1)
    Mano1_Comparar = new Mano(cartasMano1)
    Mano2 = new Mano(cartasMano2)
  }


  test("Dos manos con los mismos valores deberian ser iguales") {
    assertEquals(Mano1, Mano1_Comparar)
    assertEquals(Mano1_Comparar, Mano1)
    assertNotEquals(Mano1, Mano2)
    assertNotEquals(Mano2, Mano1)
  }


  test("toString retorna el mismo valor para objetos iguales") {
    assertEquals(Mano1.toString(), Mano1_Comparar.toString())
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(Mano1.hashCode(), Mano1_Comparar.hashCode())
  }

  test("addMember añade una carta a la mano") {
    val carta1: AbstractCarta = new CartaCuerpoCuerpo("Hanuman", 50)
    val carta2: AbstractCarta = new CartaCuerpoCuerpo("Ganesha", 30)
    val carta3: AbstractCarta = new CartaClima("Lluvia")
    Mano1.addMember(carta3)
    assert(Mano1.CartasMembers == Seq(carta1, carta2, carta3))
  }

  test("removeMember quita una carta de la mano") {
    val carta1: AbstractCarta = new CartaCuerpoCuerpo("Hanuman", 50)
    val carta2: AbstractCarta = new CartaCuerpoCuerpo("Ganesha", 30)
    val carta3: AbstractCarta = new CartaClima("Lluvia")
    Mano1.removeMember(carta2)
    assert(Mano1.CartasMembers == Seq(carta1) )
  }

}
