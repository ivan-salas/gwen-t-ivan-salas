package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController
import gwent.PackageCartas.CartaClima
import gwent.PackageJugador.GemObserver
import gwent.PackageTablero.Tablero
import gwent.PackageTablero.ZonaClima.ZonaClima

/**
 * PlayNextRound representa el estado para iniciar una ronda.
 * Sirve para demarcar entre el inicio del juego per se y la posterior eleccion de cartas y  turnos de los jugadores
 * @param context: [[GameController]] controlador del juego
 */
class PlayNextRound(context: GameController) extends GameState(context) {
  private val usuarioGame = context.usuarioJuego
  private val cpuGame = context.cpuJuego
  private val tableroGame = context.tableroJuego
  val usuarioGemObserver: GemObserver = context.usuarioGemObserver
  val cpuGemObserver: GemObserver = context.cpuGemObserver

  val gemasUsuario: Int = usuarioGame.gemas
  val gemasCPU: Int = cpuGame.gemas


  // Este estado demarca el final de la ronda anterior y el inicio de una nueva
  // Debemos hacer un par de cosas antes de empezar una nueva ronda:
  // 1. Reiniciar el tablero
  // 2. Quitar una gema al perdedor
  // 3. Checkear si alguien se quedo sin gemas, si es así se acaba el juego
  // 4. Los jugadores pueden volver a robar de sus mazos
  // 5. Si podemos jugar de nuevo, por defecto parte el usuario
  override def TurnOfUSer(): Unit = {
    context.state = new UserTurn(context)
  }

  override def OutOfGems(): Unit = {
    context.state = new GameEnds(context)
  }

  override def doAction(): Unit = {
    // Primero hay que ver quien tiene más fuerza en su parte del tablero usuario o cpu
    // Si el usuario tiene más fuerza, se le quita una gema al cpu y viceversa
    // Si tienen la misma fuerza, ambos pierden una gema
    // Luego se reinicia el tablero

    // Primero, vemos quien gano
    val FuerzaUsuario = tableroGame.zonaUsuario.sumaFuerza()
    val FuerzaCpu = tableroGame.zonaCpu.sumaFuerza()


    if (FuerzaUsuario > FuerzaCpu) {
      cpuGame.quitarGema()
      println("Perdio Cpu")
    } else if (FuerzaUsuario < FuerzaCpu) {
      usuarioGame.quitarGema()
      println("Perdio Usuario")
    } else {
      usuarioGame.quitarGema()
      cpuGame.quitarGema()
      println("Ambos perdieron")
    }

    // Segundo, llamamos a los observers para que le digan al controlador si a alguien se le acabaron las gemas
    usuarioGemObserver.update()
    cpuGemObserver.update()

    // Tercero, reiniciamos el tablero
    tableroGame.CleanTablero()
    context.GameTurnsObserver.turnos = 8

    // Cuarto, los jugadores pueden robar de sus mazos hasta 3 cartas, siempre y cuando no excedan 10 cartas en mano
    // Usuario roba 3 cartas:
    val carta1 = usuarioGame.mazo.obtenerCarta()
    val carta2 = usuarioGame.mazo.obtenerCarta()
    val carta3 = usuarioGame.mazo.obtenerCarta()
    // Las agrega a su mano
    usuarioGame.mano.addMember(carta1)
    usuarioGame.mano.addMember(carta2)
    usuarioGame.mano.addMember(carta3)

    // CPU roba 3 cartas:
    val carta4 = cpuGame.mazo.obtenerCarta()
    val carta5 = cpuGame.mazo.obtenerCarta()
    val carta6 = cpuGame.mazo.obtenerCarta()
    // Las agrega a su mano
    cpuGame.mano.addMember(carta4)
    cpuGame.mano.addMember(carta5)
    cpuGame.mano.addMember(carta6)
  }

}