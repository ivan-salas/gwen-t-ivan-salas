package cl.uchile.dcc
package TestTablero.TestZonas


import munit.FunSuite

import gwent.PackageBarajas.{Mano,Mazo}
import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import gwent.PackageCartas.{Carta, CartaAsedio, CartaCuerpoCuerpo, CartaRango}
import gwent.PackageTablero.ZonasJugadores.ZonaCpu

import scala.collection.mutable.ArrayBuffer

class ZonaCpuTest extends FunSuite {
  var filaRango: FilaRango = _
  var filaCuerpoCuerpo: FilaCuerpoCuerpo = _
  var filaAsedio: FilaAsedio = _
  var zonaCpu: ZonaCpu = _

  override def beforeEach(context: BeforeEach): Unit = {
    val cartasRango = ArrayBuffer[Carta](new CartaRango("Carta1", 5), new CartaRango("Carta2", 6))
    val cartasCuerpoCuerpo = ArrayBuffer[Carta](new CartaCuerpoCuerpo("Carta3", 7), new CartaCuerpoCuerpo("Carta4", 8))
    val cartasAsedio = ArrayBuffer[Carta](new CartaAsedio("Carta5", 9), new CartaAsedio("Carta6", 10))

    filaRango = new FilaRango(cartasRango)
    filaCuerpoCuerpo = new FilaCuerpoCuerpo(cartasCuerpoCuerpo)
    filaAsedio = new FilaAsedio(cartasAsedio)

    zonaCpu = new ZonaCpu(filaRango = filaRango,filaCuerpoCuerpo=filaCuerpoCuerpo,filaAsedio = filaAsedio)
  }

  test("La zona de la CPU debe tener las filas correctas") {
    assertEquals(zonaCpu.filaRango, filaRango)
    assertEquals(zonaCpu.filaCuerpoCuerpo, filaCuerpoCuerpo)
    assertEquals(zonaCpu.filaAsedio, filaAsedio)
  }

  test("Jugar una carta de rango debe agregarla a la fila de rango") {
    val carta = new CartaRango("Carta7", 11)
    zonaCpu.jugarCartaRango(carta)
    assert(zonaCpu.filaRango.CartasFila.contains(carta))
  }

  test("Jugar una carta cuerpo a cuerpo debe agregarla a la fila de cuerpo a cuerpo") {
    val carta = new CartaCuerpoCuerpo("Carta8", 12)
    zonaCpu.jugarCartaCuerpoCuerpo(carta)
    assert(zonaCpu.filaCuerpoCuerpo.CartasFila.contains(carta))
  }

  test("Jugar una carta de asedio debe agregarla a la fila de asedio") {
    val carta = new CartaAsedio("Carta9", 13)
    zonaCpu.jugarCartaAsedio(carta)
    assert(zonaCpu.filaAsedio.CartasFila.contains(carta))
  }
}

