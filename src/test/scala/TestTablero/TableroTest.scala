package cl.uchile.dcc.TestTablero

import cl.uchile.dcc.gwent.PackageBarajas.{Mano, Mazo}
import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta, CartaAsedio, CartaClima, CartaCuerpoCuerpo, CartaRango}
import cl.uchile.dcc.gwent.PackageJugador.{Cpu, Usuario}
import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonaClima.ZonaClima
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.{ZonaCpu, ZonaUsuario}
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class TableroTest extends FunSuite {
  var tablero: Tablero = _
  var CPU1: Cpu = _
  var Usuario_IceBear: Usuario = _


  override def beforeEach(context: BeforeEach): Unit = {
    val zonaClima = new ZonaClima(Some(new CartaClima("Lluvia")))

    val filaRango = new FilaRango(ArrayBuffer.empty)
    val filaCuerpoCuerpo = new FilaCuerpoCuerpo(ArrayBuffer.empty)
    val filaAsedio = new FilaAsedio(ArrayBuffer.empty)

    val filaRango1 = new FilaRango(ArrayBuffer.empty)
    val filaCuerpoCuerpo1 = new FilaCuerpoCuerpo(ArrayBuffer.empty)
    val filaAsedio1 = new FilaAsedio(ArrayBuffer.empty)

    val zonaCpu = new ZonaCpu(filaRango, filaCuerpoCuerpo, filaAsedio)
    val zonaUsuario = new ZonaUsuario(filaRango1, filaCuerpoCuerpo1, filaAsedio1)
    tablero = new Tablero(zonaClima, zonaCpu, zonaUsuario)


    val carta1: AbstractCarta = new CartaCuerpoCuerpo("Hanuman", 50,0)
    val carta2: AbstractCarta = new CartaClima("Lluvia")

    val cartasMazo = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartasMano = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartasMazo1 = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas
    val cartasMano1 = ArrayBuffer[AbstractCarta](carta1, carta2) // Crea un ArrayBuffer de cartas

    val mazo_test = new Mazo(cartasMazo) // Pasa el ArrayBuffer de cartas a la clase Mazo
    val mano_test = new Mano(cartasMano) // Pasa el ArrayBuffer de cartas a la clase Mano

    val mazo_test1 = new Mazo(cartasMazo) // Pasa el ArrayBuffer de cartas a la clase Mazo
    val mano_test1 = new Mano(cartasMano) // Pasa el ArrayBuffer de cartas a la clase Mano
    CPU1 = new Cpu(gemas = 2, mazo = mazo_test, mano = mano_test)
    Usuario_IceBear = new Usuario(nombre = "IceBear", gemas = 2, mazo = mazo_test1, mano = mano_test1)
  }

  test("El tablero debe tener una zona clima") {
    assert(tablero.zonaClima != null)
  }

  test("El tablero debe tener una zona CPU") {
    assert(tablero.zonaCpu != null)
  }

  test("El tablero debe tener una zona usuario") {
    assert(tablero.zonaUsuario != null)
  }

  test("Se agrega correctamente una carta cuerpo a la zona CPU") {
    //Asumimos que al metodo jugar carta se le pasa una carta directamente
    //Quizas se deba crear otra función que saque alguna carta de la mano del jugador y la juegue
    //val carta1 = CPU1.sacarCartaDeMano("Hanuman") //Por eso hicimos la funcion sacarCartaDeMano :)
    val carta1: CartaCuerpoCuerpo = new CartaCuerpoCuerpo(nombre = "Terminator", fuerza = 25,0)
    //En el futuro se puede mejorar la funcion para que pueda sacar una carta al azar en el caso de CPU
    CPU1.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaCpu.filaCuerpoCuerpo.CartasFila.length, 1)
    assertEquals(tablero.zonaCpu.filaCuerpoCuerpo.CartasFila.head, carta1)
  }

  test("Se agrega correctamente una carta asedio a la zona CPU") {
    val carta1: CartaAsedio = new CartaAsedio(nombre = "Asediador", fuerza = 15,0)
    CPU1.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaCpu.filaAsedio.CartasFila.length, 1)
    assertEquals(tablero.zonaCpu.filaAsedio.CartasFila.head, carta1)
  }

  test("Se agrega correctamente una carta rango a la zona CPU") {
    val carta1: CartaRango = new CartaRango(nombre = "Arquero", fuerza = 15,0)
    CPU1.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaCpu.filaRango.CartasFila.length, 1)
    assertEquals(tablero.zonaCpu.filaRango.CartasFila.head, carta1)
  }

  test("CPU agrega correctamente una carta clima") {
    val carta1: CartaClima = new CartaClima(nombre = "Solsito")
    CPU1.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaClima.cartaClima, Some(carta1))
  }

  test("Se agrega correctamente una carta cuerpo a la zona usuario") {
    val carta1: CartaCuerpoCuerpo = new CartaCuerpoCuerpo(nombre = "Terminator", fuerza = 25,0)
    Usuario_IceBear.mano.addMember(carta1)
    Usuario_IceBear.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaUsuario.filaCuerpoCuerpo.CartasFila.length, 1)
    assertEquals(tablero.zonaUsuario.filaCuerpoCuerpo.CartasFila.head, carta1)
  }

  test("Se agrega correctamente una carta asedio a la zona usuario") {
    val carta1: CartaAsedio = new CartaAsedio(nombre = "asedia2", fuerza = 25,0)
    Usuario_IceBear.mano.addMember(carta1)
    Usuario_IceBear.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaUsuario.filaAsedio.CartasFila.length, 1)
    assertEquals(tablero.zonaUsuario.filaAsedio.CartasFila.head, carta1)
  }

  test("Se agrega correctamente una carta rango a la zona usuario") {
    val carta1: CartaRango = new CartaRango(nombre = "Jango", fuerza = 25,0)
    Usuario_IceBear.mano.addMember(carta1)
    Usuario_IceBear.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaUsuario.filaRango.CartasFila.length, 1)
    assertEquals(tablero.zonaUsuario.filaRango.CartasFila.head, carta1)
  }

  test("USUARIO agrega correctamente una carta clima") {
    val carta1: CartaClima = new CartaClima(nombre = "Solsito")
    Usuario_IceBear.mano.addMember(carta1)
    Usuario_IceBear.jugarCarta(tablero, carta1)
    assertEquals(tablero.zonaClima.cartaClima, Some(carta1))
  }

}



