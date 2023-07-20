package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController
import gwent.PackageBarajas.{Mano, Mazo}
import gwent.PackageJugador.{Cpu, GemObserver, Usuario}

import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta}
import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonaClima.ZonaClima
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.{ZonaCpu, ZonaUsuario}

import scala.collection.mutable.ArrayBuffer
/**
 * Clase StartGame:
 *
 * Esta clase representa el estado StartGame del juego.
 * StartGame es el estado más basico del juego, en esta parte el controlador va a "controlar" la creacion de los jugadores
 * y otros valores iniciales del juego.
 */
class StartGame(context: GameController) extends GameState(context) {
  //Creamos un mazo vacio y una mano vacia para inicializar al usuario y a la cpu
  val mazoVacioUsuario = new Mazo(ArrayBuffer.empty[AbstractCarta])
  val manoVaciaUsuario = new Mano(ArrayBuffer.empty[AbstractCarta])
  val mazoVacioCpu = new Mazo(ArrayBuffer.empty[AbstractCarta])
  val manoVaciaCpu = new Mano(ArrayBuffer.empty[AbstractCarta])

  // Creamos a los jugadores
  val Usuario1 = new Usuario(nombre = "Usuario1", gemas = 2, mazo = mazoVacioUsuario, mano = manoVaciaUsuario)
  val Cpu1 = new Cpu(gemas = 2, mazo = mazoVacioCpu, mano = manoVaciaCpu)


  // Agregamos a los jugadores a los atributos del controlador
  context.usuarioJuego = Usuario1
  context.cpuJuego = Cpu1

  // Instanciamos los observadores de gemas de los jugadores
  context.usuarioGemObserver = new GemObserver(context.usuarioJuego, context)
  context.cpuGemObserver = new GemObserver(context.cpuJuego, context)
  context.GameTurnsObserver = new GameTurnsObserver(8,context)

  // Creamos el tablero del juego, para ello primero se crean las zonas del tablero
  private val cartasFilaVacia1 = ArrayBuffer.empty[Carta]
  private val cartasFilaVacia2 = ArrayBuffer.empty[Carta]
  private val cartasFilaVacia3 = ArrayBuffer.empty[Carta]
  private val cartasFilaVacia4 = ArrayBuffer.empty[Carta]
  private val cartasFilaVacia5 = ArrayBuffer.empty[Carta]
  private val cartasFilaVacia6 = ArrayBuffer.empty[Carta]


  context.zonaClimaJuego = new ZonaClima(None)
  context.zonaCpuJuego = new ZonaCpu(filaRango = new FilaRango(CartasFila = cartasFilaVacia1), filaCuerpoCuerpo = new FilaCuerpoCuerpo(CartasFila = cartasFilaVacia2), filaAsedio = new FilaAsedio(CartasFila = cartasFilaVacia3))
  context.zonaUsuarioJuego = new ZonaUsuario(filaRango = new FilaRango(CartasFila = cartasFilaVacia4), filaCuerpoCuerpo = new FilaCuerpoCuerpo(CartasFila = cartasFilaVacia5), filaAsedio = new FilaAsedio(CartasFila = cartasFilaVacia6))
  context.tableroJuego = new Tablero(zonaClima = context.zonaClimaJuego, zonaCpu = context.zonaCpuJuego, zonaUsuario = context.zonaUsuarioJuego)

  // Ya creados los jugadores, pasamos al estado PlayRound con StartARound() para iniciar una ronda del juego
  /**
   * Metodo StartARound:
   *
   * Representa la transicion desde el estado StartGame al estado PlayRound.
   */
  override def StartARound(): Unit = {
    context.state = new PlayRound(context)
  }

}
