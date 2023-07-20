package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController

import cl.uchile.dcc.gwent.PackageJugador.{Cpu, GemObserver, Usuario}
import cl.uchile.dcc.gwent.PackageTablero.Tablero

import scala.io.StdIn

class CPUTurn(context: GameController) extends GameState(context) {
  // Desde el estado CpuTurn tenemos tres posibles metodos:
  // 1. Pasar el turno, caso en que el usuario quiso pasar el turno
  // 2. Jugar una carta, caso en que el usuario quiso jugar una carta
  // 3. Jugar una carta de nuevo, caso en que CPU habia pasado su turno, y usuario habia tirado una carta antes, entonces puede volver a jugar una carta

  val usuarioJuego: Usuario = context.usuarioJuego
  val cpuJuego: Cpu = context.cpuJuego
  val tableroJuego: Tablero = context.tableroJuego
  val cpuGemObserver: GemObserver = context.cpuGemObserver


  /**
   * Metodo que se llama cuando CPU quiere pasar el turno
   */
  override def CpuPassTurn(): Unit = {
    context.CanPlayMoreTurns = true //actualizamos el valor del controlador para que se pueda jugar mas turnos
    context.currentPlayer = context.usuarioJuego //ahora el turno es de la CPU
    context.state = new UserTurn(context) //cambiamos el estado a CPUTurn
  }

  /**
   * Metodo que se llama cuando la cpu quiere jugar una carta.
   *
   *
   * Si la suma de fuerza de las cartas que tiene en el campo de batalla y en mano es mayor que la del campo de
   * batalla del oponente, entonces juega una carta al azar de su mano hasta que el oponente pase su turno.
   *
   *
   * Si la suma de fuerza de las cartas que tiene en el campo de batalla y en mano es menor que la del campo de
   * batalla del oponente, entonces intentará jugar una carta de clima al azar. En caso de que no tenga una carta
   * de clima, pasará su turno.
   */
  override def CpuPlayOneCard(): Unit = {
    val sumaFuerzaCpu = cpuJuego.mano.sumaFuerza() + tableroJuego.zonaCpu.sumaFuerza() // Suma de fuerza de las cartas en mano y en campo de batalla
    val sumaFuerzaOponente = tableroJuego.zonaUsuario.sumaFuerza() // Suma de fuerza de las cartas en campo de batalla del oponente

    // Si la suma de fuerza de las cartas que tiene en el campo de batalla y en mano es mayor que la del campo de
    // batalla del oponente, entonces juega una carta al azar de su mano
    if (sumaFuerzaCpu > sumaFuerzaOponente) {
      val cartaJugar = cpuJuego.mano.obtenerCartaAzar()
      cpuJuego.jugarCarta(tablero = tableroJuego, carta = cartaJugar)
    }
    else { // Si la suma de fuerza es menor que la del oponente, se juega una carta clima al azar
      val cartaJugar = cpuJuego.mano.obtenerCartaClimaAzar()
      cpuJuego.jugarCarta(tablero = tableroJuego, carta = cartaJugar)
    }
    
    context.GameTurnsObserver.turnos -= 1 //se resta un turno
    context.currentPlayer = context.usuarioJuego //ahora el turno es del usuario
    context.state = new UserTurn(context) //cambiamos el estado a UserTurn
  }

  /**
   * Metodo que se llama cuando el usuario quiere jugar una carta de nuevo
   */
  override def CpuPlayCardAgain(): Unit = {
    val sumaFuerzaCpu = cpuJuego.mano.sumaFuerza() + tableroJuego.zonaCpu.sumaFuerza() // Suma de fuerza de las cartas en mano y en campo de batalla
    val sumaFuerzaOponente = tableroJuego.zonaUsuario.sumaFuerza() // Suma de fuerza de las cartas en campo de batalla del oponente

    // Si la suma de fuerza de las cartas que tiene en el campo de batalla y en mano es mayor que la del campo de
    // batalla del oponente, entonces juega una carta al azar de su mano
    if (sumaFuerzaCpu > sumaFuerzaOponente) {
      val cartaJugar = cpuJuego.mano.obtenerCartaAzar()
      cpuJuego.jugarCarta(tablero = tableroJuego, carta = cartaJugar)
    }
    else { // Si la suma de fuerza es menor que la del oponente, se juega una carta clima al azar
      val cartaJugar = cpuJuego.mano.obtenerCartaClimaAzar()
      cpuJuego.jugarCarta(tablero = tableroJuego, carta = cartaJugar)
    }

    context.GameTurnsObserver.turnos -= 1 //se resta un turno
    context.currentPlayer = context.cpuJuego //sigue siendo el turno de la CPU
    context.state = new CPUTurn(context) //seguimos en el estado a CpuTurn
  }

  override def PlayNextRound(): Unit = {
    context.state = new PlayNextRound(context)
  }
  
  override def doAction(): Unit = {
    //Llamemos a la instancia de game observer para comunicar si el usuario tiene gemas o no
    context.GameTurnsObserver.update()
  }

}
