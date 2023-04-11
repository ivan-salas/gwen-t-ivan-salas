package cl.uchile.dcc
package TestJugador
import munit.FunSuite
import gwent.PackageJugador.Cpu

import cl.uchile.dcc.gwent.PackageCartas.{CartaClima, CartaUnidad}
class CpuTest extends FunSuite {
  var CPU1: Cpu = _
  var CPU_Vacio: Cpu = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1 = new CartaUnidad("Hanuman", 50)
    val carta2 = new CartaClima("Lluvia")
    val mazo_test = List(carta1, carta2)
    val mano_test = List(carta1, carta2)
    CPU1 = new Cpu( gemas = 2, mazo = mazo_test, mano = mano_test)
  }


  //PRIMEROS TESTS: CASO NORMAL Y CASO BORDE PARA EL CONTADOR DE GEMAS DE UN JUGADOR CPU
  //NOTA: NO SE INCLUYE TEST PARA NOMBRE PORQUE CPU NO NECESITA UN NOMBRE PERSONALIZADO
  test("La CPU no puede tener más de 2 gemas") {
    assert(CPU1.gemas <= 2)
  }

  test("La CPU no puede tener gemas negativas") {
    assert( CPU1.gemas >= 0)
  }

  //SEGUNDOS TEST: CASO NORMAL Y CASO BORDE PARA MAZO DE CARTAS DE UN JUGADOR CPU
  test("La CPU debe tener un numero de cartas en su mazo") {
    assertEquals(CPU1.ObtenerNumeroCartasMazo(), 2)
  }

  test("La CPU no puede tener más de 25 cartas en su mazo") {
    assert(CPU1.ObtenerNumeroCartasMazo() <= 25)
  }

  test("La CPU no puede tener un numero negativo de cartas en su mazo") {
    assert(CPU1.ObtenerNumeroCartasMazo() >= 0)
  }

  //TERCEROS TEST: CASO NORMAL Y CASO BORDE PARA LA MANO DE CARTAS DE UN JUGADOR CPU
  test("La CPU debe tener un numero de cartas en su mano") {
    assertEquals(CPU1.ObtenerNumeroCartasMano(), 2)
  }

  test("Un Usuario no puede tener más de 10 cartas en su mano") {
    assert(CPU1.ObtenerNumeroCartasMano() <= 10)
  }

  test("Un Usuario no puede tener un numero negativo de cartas en su mano") {
    assert(CPU1.ObtenerNumeroCartasMano() >= 0)
  }
}