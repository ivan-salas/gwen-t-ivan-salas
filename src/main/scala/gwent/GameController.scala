package cl.uchile.dcc
package gwent

import gwent.PackageBarajas.{Mano, Mazo}
import gwent.PackageCartas.{AbstractCarta, Carta, CartaClima}
import gwent.PackageEstados.{CardsShufNPick, GameEnds, GameState, GameTurnsObserver, StartGame, UserTurn}
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
  var zonaClimaJuego: ZonaClima = _
  var zonaCpuJuego: ZonaCpu = _
  var zonaUsuarioJuego: ZonaUsuario = _
  var tableroJuego: Tablero = _

  /** 6. Numero de turnos en la ronda
   * Cada ronda consta de 10 turnos que se van gastando entre el usuario y la cpu
   * */
  var GameTurnsObserver: GameTurnsObserver = _

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
    state.TurnOfUSer() // Ahora estamos en el estado UserTurn
  }

  /** 4. Metodo para que Usuario pase de turno.
   * */
  def UserPassTurn(): Unit = {
    // Aqui estamos en el estado UserTurn
    state.UserPassTurn()
    state.doAction() //Esto sirve para updatear los observers
  }

  /** 5. Metodo para que Usuario juegue una carta.
   * */
  def UserPlayOneCard(): Unit = {
    // Aqui estamos en el estado UserTurn
    
    val CartasEnMano = usuarioJuego.mano.CartasMembers.map(_.nombre) // Obtenemos las cartas en mano del usuario
    val IndiceCarta = userInterface.promptSelection(CartasEnMano) // Le pedimos al usuario que elija el indice de la carta de su mano
    state.UserPlayOneCard(IndiceCarta)
    state.doAction() //Esto sirve para updatear los observers
  }

  /** 6. Metodo para Usuario juegue una carta de nuevo.
   *  Este metodo solo se puede ejecutar si CanPlayMoreTurns es true
   * */
  def UserPlayCardAgain(): Unit = {
    if(!CanPlayMoreTurns) throw new Exception("No puedes jugar otra carta en este turno")
    // Aqui estamos en el estado UserTurn
    
    val CartasEnMano = usuarioJuego.mano.CartasMembers.map(_.nombre) // Obtenemos las cartas en mano del usuario
    val IndiceCarta = userInterface.promptSelection(CartasEnMano) // Le pedimos al usuario que elija el indice de la carta de su mano
    state.UserPlayCardAgain(IndiceCarta)
    state.doAction() //Esto sirve para updatear los observers
  }

  /** 7. Metodo para que CPU pase de turno.
   * */
  def CpuPassTurn(): Unit = {
    // Aqui estamos en el estado UserTurn
    state.CpuPassTurn()
    state.doAction() //Esto sirve para updatear los observers
  }

  /** 8. Metodo para que Cpu juegue una carta.
   * */
  def CpuPlayOneCard(): Unit = {
    // Aqui estamos en el estado UserTurn
    state.CpuPlayOneCard()
    state.doAction() //Esto sirve para updatear los observers
  }

  /** 9. Metodo para Cpu juegue una carta de nuevo.
   * */
  def CpuPlayCardAgain(): Unit = {
    if (!CanPlayMoreTurns) throw new Exception("No puedes jugar otra carta en este turno")
    // Aqui estamos en el estado UserTurn
    state.CpuPlayCardAgain()
    state.doAction() //Esto sirve para updatear los observers
  }

  /** 10. Metodo para jugar la siguiente ronda.
   * */
  def PlayNextRound(): Unit = {
    state.PlayNextRound() //Pasamos al estado PlayNextRound
    state.doAction() // Hacemos doAction() para que el estado haga su accion (En este caso son varias cositas)
    // el doAction de este estado llama por ejemplo al gemObserver y pude que el juego termine aqui
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
  
  //Funciones extras
  
  /* Getter del estado del controlador
  * */
  def getState: GameState = {
    state
  }

}

