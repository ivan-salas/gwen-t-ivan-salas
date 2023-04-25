package cl.uchile.dcc
package TestBaraja

import cl.uchile.dcc.gwent.PackageBarajas.Mazo
import cl.uchile.dcc.gwent.PackageCartas.{Carta, CartaClima, CartaUnidad}
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

//CLASE DE LOS TEST PARA LA CLASE MAZO
class MazoTest extends FunSuite{
  var Mazo1: Mazo = _
  var Mazo1_Comparar: Mazo = _
  var Mazo2: Mazo = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: Carta = new CartaUnidad("Hanuman", 50)
    val carta2: Carta = new CartaUnidad("Ganesha", 30)
    val carta3: Carta = new CartaClima("Lluvia")
    val cartasMazo1 = ArrayBuffer[Carta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartasMazo2 = ArrayBuffer[Carta](carta1,carta2, carta3)

    Mazo1 = new Mazo(cartasMazo1)
    Mazo1_Comparar = new Mazo(cartasMazo1)
    Mazo2 = new Mazo(cartasMazo2)
  }


  test("dos MAZOS con los mismos valores debiesen ser iguales") {
    assertEquals(Mazo1, Mazo1_Comparar)
    assertEquals(Mazo1_Comparar, Mazo1)
    assertNotEquals(Mazo1, Mazo2)
    assertNotEquals(Mazo2, Mazo1)
  }


  test("toString retorna el mismo valor para objetos iguales") {
    assertEquals(Mazo1.toString(), Mazo1_Comparar.toString())
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(Mazo1.hashCode(), Mazo1_Comparar.hashCode())
  }

  test("addMember añade una carta al mazo") {
    val carta1: Carta = new CartaUnidad("Hanuman", 50)
    val carta2: Carta = new CartaUnidad("Ganesha", 30)
    val carta3: Carta = new CartaClima("Lluvia")
    Mazo1.addMember(carta3)
    assert(Mazo1.CartasMembers == Seq(carta1, carta2, carta3))
  }

  test("removeMember quita una carta al mazo") {
    val carta1: Carta = new CartaUnidad("Hanuman", 50)
    val carta2: Carta = new CartaUnidad("Ganesha", 30)
    val carta3: Carta = new CartaClima("Lluvia")
    Mazo1.removeMember(carta2)
    assert(Mazo1.CartasMembers == Seq(carta1))
  }

  test("obtener carta deberia removerle una carta al mazo (que posteriormente se da a mano)") {
    val carta1: Carta = new CartaUnidad("Hanuman", 50)
    val carta2: Carta = new CartaUnidad("Ganesha", 30)
    val carta3: Carta = new CartaClima("Lluvia")
    Mazo1.obtenerCarta()
    val largoMazo = Mazo1.CartasMembers.length
    assert(largoMazo == 1)
  }


}
