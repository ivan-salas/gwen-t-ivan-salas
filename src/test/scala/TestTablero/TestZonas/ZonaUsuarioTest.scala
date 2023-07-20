package cl.uchile.dcc
package TestTablero.TestZonas

import munit.FunSuite

import gwent.PackageBarajas.{Mano,Mazo}
import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import gwent.PackageCartas.{Carta, CartaAsedio, CartaCuerpoCuerpo, CartaRango}
import gwent.PackageTablero.ZonasJugadores.ZonaUsuario

import scala.collection.mutable.ArrayBuffer

class ZonaUsuarioTest extends FunSuite {
  var filaRango: FilaRango = _
  var filaCuerpoCuerpo: FilaCuerpoCuerpo = _
  var filaAsedio: FilaAsedio = _
  var zonaUsuario: ZonaUsuario = _

  override def beforeEach(context: BeforeEach): Unit = {
    val cartasRango = ArrayBuffer[Carta](new CartaRango("Carta1", 5,0), new CartaRango("Carta2", 6,0))
    val cartasCuerpoCuerpo = ArrayBuffer[Carta](new CartaCuerpoCuerpo("Carta3", 7,0), new CartaCuerpoCuerpo("Carta4", 8,0))
    val cartasAsedio = ArrayBuffer[Carta](new CartaAsedio("Carta5", 9,0), new CartaAsedio("Carta6", 10,0))

    filaRango = new FilaRango(cartasRango)
    filaCuerpoCuerpo = new FilaCuerpoCuerpo(cartasCuerpoCuerpo)
    filaAsedio = new FilaAsedio(cartasAsedio)

    zonaUsuario = new ZonaUsuario(filaRango,filaCuerpoCuerpo,filaAsedio)
  }

  test("La zona de la CPU debe tener las filas correctas") {
    assertEquals(zonaUsuario.filaRango, filaRango)
    assertEquals(zonaUsuario.filaCuerpoCuerpo, filaCuerpoCuerpo)
    assertEquals(zonaUsuario.filaAsedio, filaAsedio)
  }

  test("Jugar una carta de rango debe agregarla a la fila de rango") {
    val carta = new CartaRango("Carta7", 11,0)
    zonaUsuario.jugarCartaRango(carta)
    assert(zonaUsuario.filaRango.CartasFila.contains(carta))
  }

  test("Jugar una carta cuerpo a cuerpo debe agregarla a la fila de cuerpo a cuerpo") {
    val carta = new CartaCuerpoCuerpo("Carta8", 12,0)
    zonaUsuario.jugarCartaCuerpoCuerpo(carta)
    assert(zonaUsuario.filaCuerpoCuerpo.CartasFila.contains(carta))
  }

  test("Jugar una carta de asedio debe agregarla a la fila de asedio") {
    val carta = new CartaAsedio("Carta9", 13,0)
    zonaUsuario.jugarCartaAsedio(carta)
    assert(zonaUsuario.filaAsedio.CartasFila.contains(carta))
  }
}