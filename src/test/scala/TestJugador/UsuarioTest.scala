package cl.uchile.dcc
package TestJugador
import munit.FunSuite
import gwent.PackageJugador.Usuario

import cl.uchile.dcc.gwent.PackageCartas.CartaUnidad
import cl.uchile.dcc.gwent.PackageCartas.CartaClima
class UsuarioTest extends FunSuite {
  var Usuario_IceBear: Usuario = _
  var Usuario_Vacio: Usuario = _

  override def beforeEach(context: BeforeEach): Unit = {
    val carta1 = new CartaUnidad("Hanuman", 50)
    val carta2 = new CartaClima("Lluvia")
    val mazo_test = List(carta1, carta2)
    val mano_test = List(carta1, carta2)
    Usuario_IceBear = new Usuario(nombre = "IceBear", gemas = 2, mazo = mazo_test, mano = mano_test)
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
    assert(Usuario_IceBear.gemas <= 2)
  }

  test("Un Usuario no puede tener gemas negativas") {
    assert( Usuario_IceBear.gemas >= 0)
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
}


