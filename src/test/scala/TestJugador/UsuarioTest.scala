package cl.uchile.dcc
package TestJugador
import munit.FunSuite
import gwent.PackageJugador.Usuario

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta, CartaClima, CartaCuerpoCuerpo}
import munit.Clue.generate

import scala.collection.mutable.ArrayBuffer
class UsuarioTest extends FunSuite {
  var Usuario_IceBear: Usuario = _
  var Usuario_IceBear_Comparar: Usuario = _
  var Usuario_FireBear: Usuario = _
  var Usuario_Vacio: Usuario = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1: AbstractCarta = new CartaCuerpoCuerpo("Hanuman", 50,0)
    val carta2: AbstractCarta = new CartaClima("Lluvia")
    val cartasMazo = ArrayBuffer[AbstractCarta](carta1,carta2) // Crea un ArrayBuffer de cartas
    val cartasMano = ArrayBuffer[AbstractCarta](carta1,carta2) // Crea un ArrayBuffer de cartas
    val mazo_test = new Mazo(cartasMazo) // Pasa el ArrayBuffer de cartas a la clase Mazo
    val mano_test = new Mano(cartasMano) // Pasa el ArrayBuffer de cartas a la clase Mano

    Usuario_IceBear = new Usuario(nombre = "IceBear", gemas = 2, mazo = mazo_test, mano = mano_test)
    Usuario_IceBear_Comparar = new Usuario(nombre = "IceBear", gemas = 2, mazo = mazo_test, mano = mano_test)
    Usuario_FireBear = new Usuario(nombre = "FireBear", gemas = 1, mazo = mazo_test, mano = mano_test)
  }

  //PRIMEROS TEST: CASO NORMAL Y CASO BORDE PARA NOMBRE DE UN JUGADOR USUARIO
  test("Un Usuario debe tener un nombre") {
    assertEquals(Usuario_IceBear.nombre, "IceBear")
  }

  test("Un Usuario no puede tener un nombre vacio") {
    assertNotEquals(Usuario_IceBear.nombre,"",clue="")
  }

  //SEGUNDOS TESTS: CASO NORAML Y CASO BORDE PARA CONTADOR DE GEMAS DE UN JUGADOR USUARIO
  test("Un Usuario no puede tener más de 2 gemas") {
    assert(Usuario_IceBear.getGemas <= 2)
  }

  test("Un Usuario no puede tener gemas negativas") {
    assert( Usuario_IceBear.getGemas >= 0)
  }

  //TERCEROS TEST: CASO NORMAL Y CASO BORDE PARA MAZO DE CARTAS DE UN JUGADOR USUARIO
  test("Un Usuario debe tener un numero de cartas en su mazo") {
    assertEquals(Usuario_IceBear.ObtenerNumeroCartasMazo(), 2)
  }

  test("Un Usuario no puede tener más de 25 cartas en su mazo") {
    assert(Usuario_IceBear.ObtenerNumeroCartasMazo() <= 25)
  }

  test("Un Usuario no puede tener un numero negativo de cartas en su mazo") {
    assert(Usuario_IceBear.ObtenerNumeroCartasMazo() >= 0)
  }

  //CUARTOS TEST: CASO NORMAL Y CASO BORDE PARA LA MANO DE CARTAS DE UN JUGADOR USUARIO
  test("Un Usuario debe tener un numero de cartas en su mano") {
    assertEquals(Usuario_IceBear.ObtenerNumeroCartasMano(), 2)
  }

  test("Un Usuario no puede tener más de 10 cartas en su mano") {
    assert(Usuario_IceBear.ObtenerNumeroCartasMano() <= 10)
  }

  test("Un Usuario no puede tener un numero negativo de cartas en su mano") {
    assert(Usuario_IceBear.ObtenerNumeroCartasMano() >= 0)
  }

  test("dos usuarios con los mismos valores deberian ser iguales") {
    assertEquals(Usuario_IceBear, Usuario_IceBear_Comparar)
    assertEquals(Usuario_IceBear_Comparar, Usuario_IceBear)
    assertNotEquals(Usuario_IceBear, Usuario_FireBear)
    assertNotEquals(Usuario_FireBear, Usuario_IceBear)
  }

  test("hashCode() retorna el mismo valor para objetos iguales") {
    assertEquals(Usuario_IceBear.hashCode(), Usuario_IceBear_Comparar.hashCode())
    assertNotEquals(Usuario_IceBear.hashCode(), Usuario_FireBear.hashCode())
  }

  test("robarCarta debe quitar una carta del mazo y darsela a la mano") {
    val largo_previo_mazo=Usuario_IceBear.ObtenerNumeroCartasMazo()
    val largo_previo_mano=Usuario_IceBear.ObtenerNumeroCartasMano()
    Usuario_IceBear.robarCartaDeMazo()
    val largo_post_mazo=Usuario_IceBear.ObtenerNumeroCartasMazo()
    val largo_post_mano=Usuario_IceBear.ObtenerNumeroCartasMano()
    assert(largo_previo_mazo==largo_post_mazo+1)
    assert(largo_previo_mano==largo_post_mano-1)
  }

  test("test para el setter de gemas"){
    Usuario_IceBear.setGemas(3)
    assertEquals(Usuario_IceBear.getGemas,3)
  }

}


