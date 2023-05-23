package cl.uchile.dcc
package TestJugador
import munit.FunSuite
import gwent.PackageJugador.{Cpu, Jugador, Usuario}

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta, CartaClima, CartaCuerpoCuerpo}

import scala.collection.mutable.ArrayBuffer

//CLASE TEST PARA CPU
class CpuTest extends FunSuite {
  var CPU1: Cpu = _
  var CPU1_COMPARAR: Cpu = _
  var CPU2: Cpu = _
  var Usuario_IceBear: Usuario = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: AbstractCarta = new CartaCuerpoCuerpo("Hanuman", 50)
    val carta2: AbstractCarta = new CartaClima("Lluvia")
    val cartasMazo = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartasMano = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val mazo_test = new Mazo(cartasMazo) // Pasa el ArrayBuffer de cartas a la clase Mazo
    val mano_test = new Mano(cartasMano) // Pasa el ArrayBuffer de cartas a la clase Mano
    CPU1 = new Cpu( gemas = 2, mazo = mazo_test, mano = mano_test)
    CPU1_COMPARAR = new Cpu( gemas = 2, mazo = mazo_test, mano = mano_test)
    CPU2 = new Cpu(gemas = 1, mazo = mazo_test, mano = mano_test)
    Usuario_IceBear = new Usuario(nombre = "IceBear", gemas = 2, mazo = mazo_test, mano = mano_test)
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

  test("La CPU no puede tener más de 10 cartas en su mano") {
    assert(CPU1.ObtenerNumeroCartasMano() <= 10)
  }

  test("La CPU no puede tener un numero negativo de cartas en su mano") {
    assert(CPU1.ObtenerNumeroCartasMano() >= 0)
  }

  test("Dos CPU con los mismos valores deberian ser iguales") {
    assertEquals(CPU1, CPU1_COMPARAR)
    assertEquals(CPU1_COMPARAR, CPU1)
    assertNotEquals(CPU1, CPU2)
    assertNotEquals(CPU2, CPU1)
  }


  test("toString retorna el mismo valor para objetos iguales") {
    assertEquals(CPU1.toString(), CPU1_COMPARAR.toString())
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(CPU1.hashCode(), CPU1_COMPARAR.hashCode())
  }




}