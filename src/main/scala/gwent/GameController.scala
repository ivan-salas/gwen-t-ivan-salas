package cl.uchile.dcc
package gwent

import gwent.PackageBarajas.{Mano, Mazo}
import gwent.PackageCartas.{AbstractCarta, Carta}
import gwent.PackageEstados.{CardsShufNPick, GameState, StartGame, UserTurn}
import gwent.PackageJugador.{AbstractJugador, Cpu, GemObserver, Usuario}
import gwent.PackageTablero.ZonaClima.ZonaClima
import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import gwent.PackageTablero.ZonasJugadores.{ZonaCpu, ZonaUsuario}
import gwent.PackageTablero.{Tablero, ZonasJugadores}
import gwent.PackageView.UserInterface

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
import scala.language.postfixOps

class GameController(val userInterface: UserInterface) {

  // A continuacion definimos los atributos del controlador

  /** 0. Estado actual del juego, seteado inicialmente a StartGame */
  var state: GameState = new StartGame(this)

  /** 1. El jugador de usuario en el juego */
  var usuarioJuego: Usuario = _
  var usuarioGemObserver: GemObserver = _

  /** 2. El jugador de la CPU en el juego */
  var cpuJuego: Cpu = _
  var cpuGemObserver: GemObserver = _

  /** 3. El jugador que esta jugando actualmente */
  var currentPlayer: AbstractJugador = _

  /** 4. Booleano si el currentPlayer puede tirar más de 1 carta en su turno */
  var CanPlayMoreTurns: Boolean = false

  /** 5. Tablero del juego */
  var tableroJuego: Tablero = _
  var zonaClimaJuego: ZonaClima = _
  var zonaCpuJuego: ZonaCpu = _
  var zonaUsuarioJuego: ZonaUsuario = _
  


  // A continuación definimos los metodos del controlador:

  /** 1. Metodo para iniciar una ronda del juego. */
  def StartARound(): Unit = {
    state.StartARound()
  }

  /** 2. Metodo para avanzar al estado CardsShufNPick */
  def GameBegins(): Unit = {
    state.GameBegins() // Ahora estamos en el estado CardsShufNPick
    state.doAction() // Hacemos doAction() para que el estado haga su accion
  }

  /** 3. Metodo para ir al turno del usuario. */
  def TurnOfUser(): Unit = {
    val CartasEnMano = usuarioJuego.mano.CartasMembers.map(_.nombre) // Obtenemos las cartas en mano del usuario
    val IndiceCarta = userInterface.promptSelection(CartasEnMano) // Le pedimos al usuario que elija el indice de la carta de su mano
    val CartaElegida = usuarioJuego.mano.CartasMembers(IndiceCarta) // Obtenemos la carta elegida llamando al indice del arraybuffer

    //Jugamos la carta escogida
    usuarioJuego.jugarCarta(tableroJuego, CartaElegida) // Jugamos la carta elegida

    state.TurnOfUSer() // Ahora estamos en el estado UserTurn
  }

  /** 4. Metodo para que Usuario pase de turno.
   * */
  def UserPassTurn(): Unit = {
    state.UserPassTurn()
  }

  /** 5. Metodo para que Usuario juegue una carta.
   * */
  def UserPlayOneCard(): Unit = {
    state.UserPlayOneCard()
  }

  /** 6. Metodo para Usuario juegue una carta de nuevo.
   * */
  def UserPlayCardAgain(): Unit = {
    state.UserPlayCardAgain()
  }

  /** 7. Metodo para que CPU pase de turno.
   * */
  def CpuPassTurn(): Unit = {
    state.CpuPassTurn()
  }

  /** 8. Metodo para que Cpu juegue una carta.
   * */
  def CpuPlayOneCard(): Unit = {
    state.CpuPlayOneCard()
  }

  /** 9. Metodo para Cpu juegue una carta de nuevo.
   * */
  def CpuPlayCardAgain(): Unit = {
    state.CpuPlayCardAgain()
  }

  /** 10. Metodo para jugar la siguiente ronda.
   * */
  def PlayNextRound(): Unit = {
    state.PlayNextRound()
  }

  /** 11. Metodo para cuando un jugador esta OutOfGems
   * */
  def OutOfGems(): Unit = {
    state.OutOfGems()
  }

  /** 12. Metodo para que el estado haga doAction()
   * */
  def doAction(): Unit = {
    state.doAction()
  }

}

