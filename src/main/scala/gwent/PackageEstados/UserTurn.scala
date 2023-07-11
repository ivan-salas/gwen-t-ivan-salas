package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController
import gwent.PackageCartas.Carta
import gwent.PackageJugador.{Cpu, Usuario}
import gwent.PackageTablero.Tablero

import scala.io.StdIn

class UserTurn(context: GameController) extends GameState(context) {
  // Desde el estado UserTurn tenemos tres posibles metodos:
  // 1. Pasar el turno, caso en que el usuario quiso pasar el turno
  // 2. Jugar una carta, caso en que el usuario quiso jugar una carta
  // 3. Jugar una carta de nuevo, caso en que CPU habia pasado su turno, y usuario habia tirado una carta antes, entonces puede volver a jugar una carta

  val usuarioJuego: Usuario = context.usuarioJuego
  val cpuJuego: Cpu = context.cpuJuego
  val tableroJuego: Tablero = context.tableroJuego


  /**
   * Metodo que se llama cuando el usuario quiere pasar el turno
   */
  override def UserPassTurn(): Unit = {
    context.CanPlayMoreTurns = true //actualizamos el valor del controlador para que se pueda jugar mas turnos
    context.currentPlayer = context.cpuJuego //ahora el turno es de la CPU
    context.state = new CPUTurn(context) //cambiamos el estado a CPUTurn
  }

  /**
   * Metodo que se llama cuando el usuario quiere jugar una carta
   *
   */
  override def UserPlayOneCard(): Unit = {
    // Cómo definimos la carta que el usuario quiere jugar?
    // 1. Primero, el usuario debe elegir una carta de su mano (supongamos que la carta elegida es cartaElegida)
    // y supongamos que el usuario nos da el nombre de la carta que quiere jugar, así usamos el metodo sacarCarta(nombre: String) de la mano
    // 2. Luego, se llama al metodo jugarCarta del usuario que se encarga de jugar la carta en la zona correspondiente

    // Advertencia al ayudante:
    // La verdad esta parte de programar como el usuario elige una carta en especifico de su mano
    // no me quedo 100% claro, pero asumí que el usuario podría escribir el nombre de la carta que quiere jugar
    // y así usar StdIn.readLine() para recibir el nombre de la carta como input
    val nombreCarta = StdIn.readLine() // El usuario elige una carta de su mano


    val cartaElegida = usuarioJuego.mano.sacarCarta(nombreCarta) // Sacamos la carta de la mano del usuario
    usuarioJuego.jugarCarta(tablero = tableroJuego, carta = cartaElegida) // Jugamos la carta en la zona correspondiente

    context.currentPlayer = context.cpuJuego //ahora el turno es de la CPU
    context.state = new CPUTurn(context) //cambiamos el estado a CpuTurn
  }

  /**
   * Metodo que se llama cuando el usuario quiere jugar una carta de nuevo
   */
  override def UserPlayCardAgain(): Unit = {
    val nombreCarta = StdIn.readLine() // El usuario elige una carta de su mano

    val cartaElegida = usuarioJuego.mano.sacarCarta(nombreCarta) // Sacamos la carta de la mano del usuario
    usuarioJuego.jugarCarta(tablero = tableroJuego, carta = cartaElegida) // Jugamos la carta en la zona correspondiente

    context.currentPlayer = context.usuarioJuego // nos aseguramos que el turno sea del usuario
    context.state = new UserTurn(context) //cambiamos el estado a UserTurn
  }

  override def OutOfGems(): Unit = {
    context.state = new GameEnds(context)
  }

  override def PlayNextRound(): Unit = {
    context.state = new PlayRound(context)
  }

}
