package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController
import gwent.PackageCartas.Carta
import gwent.PackageJugador.{Cpu, GemObserver, Usuario}
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
  val usuarioGemObserver: GemObserver = context.usuarioGemObserver
  val GameTurnsObserver: GameTurnsObserver = context.GameTurnsObserver


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
  override def UserPlayOneCard(indiceCarta: Int): Unit = {
    // Cómo definimos la carta que el usuario quiere jugar?
    // 1. Primero, el usuario debe elegir una carta de su mano (supongamos que la carta elegida es cartaElegida)
    // y supongamos que el usuario nos da el nombre de la carta que quiere jugar, así usamos el metodo sacarCarta(nombre: String) de la mano
    // 2. Luego, se llama al metodo jugarCarta del usuario que se encarga de jugar la carta en la zona correspondiente

    // Advertencia al ayudante:
    // La verdad esta parte de programar como el usuario elige una carta en especifico de su mano
    // no me quedo 100% claro, pero asumí que el usuario podría escribir el nombre de la carta que quiere jugar
    // y así usar StdIn.readLine() para recibir el nombre de la carta como input

    val CartaElegida = usuarioJuego.mano.CartasMembers(indiceCarta) // Obtenemos la carta elegida llamando al indice del arraybuffer
    //println("Carta elegida: " + CartaElegida.nombre)
    //println(" ")


    //Jugamos la carta escogida
    usuarioJuego.jugarCarta(tableroJuego, CartaElegida) // Jugamos la carta elegida
    usuarioJuego.mano.removeMember(CartaElegida) // Eliminamos la carta de la mano del usuario



    //Cuando el usuario desea jugar solo una carta, entonces el turno cambia a la cpu
    context.GameTurnsObserver.turnos -= 1 // Se ha gastado un turno
    context.currentPlayer = context.cpuJuego //ahora el turno es de la CPU
    context.state = new CPUTurn(context) //cambiamos el estado a CPUTurn
  }

  /**
   * Metodo que se llama cuando el usuario quiere jugar una carta de nuevo
   */
  override def UserPlayCardAgain(indiceCarta: Int): Unit = {

    val CartaElegida = usuarioJuego.mano.CartasMembers(indiceCarta) // Obtenemos la carta elegida llamando al indice del arraybuffer

    //Jugamos la carta escogida
    usuarioJuego.jugarCarta(tableroJuego, CartaElegida) // Jugamos la carta elegida
    usuarioJuego.mano.removeMember(CartaElegida) // Eliminamos la carta de la mano del usuario

    //Cuando el usuario desea jugar una carta de nuevo, entonces no cambia el turno a CPU
    context.GameTurnsObserver.turnos -= 1
    context.currentPlayer = context.usuarioJuego // nos aseguramos que el turno sea del usuario
    context.state = new UserTurn(context) //cambiamos el estado a UserTurn
  }

  override def PlayNextRound(): Unit = {
    context.state = new PlayNextRound(context)
  }

  override def doAction(): Unit = {
    //Llamemos a la instancia de game observer para comunicar si el usuario tiene gemas o no
    GameTurnsObserver.update()
  }


}
