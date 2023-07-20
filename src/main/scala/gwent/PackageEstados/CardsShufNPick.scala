package cl.uchile.dcc
package gwent.PackageEstados

import gwent.GameController
import gwent.PackageBarajas.{Mano, Mazo}
import gwent.PackageCartas.*
import gwent.PackageJugador.{AbstractJugador, Cpu, Usuario}

import scala.collection.mutable.ArrayBuffer

/**
 * Este estado representa el momento en que se reparten las cartas a los jugadores.
 * Por simplicidad vamos a definir un set estandar para todos los juegos
 * @param context el [[GameController]] del juego
 */
class CardsShufNPick(context: GameController) extends GameState(context) {

  //Definimos 25 cartas para el mazo de cada uno:
  // Nota: para simplificar el juego, se asume que el mazo de los jugadores es el mismo en base a un set estandar
  // es decir en cada juego siempre van a partir con el mismo mazo original de 25 cartas
  private val cartas1 = ArrayBuffer.empty[AbstractCarta]
  private val cartas2 = ArrayBuffer.empty[AbstractCarta]

  // Se consideran las siguientes cartas: 21 carta de unidad (con o sin efecto) y 4 carta de clima
  // Las cartas de unidad se dividen en 7 de asedio, 7 cuerpo a cuerpo y 7 de rango

  // Primero definimos 7 Cartas Rango
  cartas1 += new CartaRango(nombre="GreenArrow",fuerza=3,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre="GreenArrow",fuerza=3,fuerzaTemp = 0)

  cartas1 += new CartaRango(nombre = "Hawkeye", fuerza = 2,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre = "Hawkeye", fuerza = 2,fuerzaTemp = 0)

  cartas1 += new CartaRango(nombre = "Legolas", fuerza = 1,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre = "Legolas", fuerza = 1,fuerzaTemp = 0)

  cartas1 += new CartaRango(nombre = "RobinHood", fuerza = 2,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre = "RobinHood", fuerza = 2,fuerzaTemp = 0)

  cartas1 += new CartaRango(nombre = "Sniper",fuerza = 4,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre = "Sniper",fuerza = 4,fuerzaTemp = 0)

  cartas1 += new CartaRango(nombre = "CrossBower",fuerza = 3,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre = "CrossBower",fuerza = 3,fuerzaTemp = 0)

  cartas1 += new CartaRango(nombre = "Archie",fuerza = 1,fuerzaTemp = 0)
  cartas2 += new CartaRango(nombre = "Archie",fuerza = 1,fuerzaTemp = 0)

  // Luego definimos 7 Cartas Cuerpo a Cuerpo
  cartas1 += new CartaCuerpoCuerpo(nombre = "Bob Charlie",fuerza = 5,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Bob Charlie",fuerza = 5,fuerzaTemp = 0)

  cartas1 += new CartaCuerpoCuerpo(nombre = "Dragon Chan",fuerza = 4,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Dragon Chan",fuerza = 4,fuerzaTemp = 0)

  cartas1 += new CartaCuerpoCuerpo(nombre = "Super Macho Man",fuerza = 3,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Super Macho Man",fuerza = 3,fuerzaTemp = 0)

  cartas1 += new CartaCuerpoCuerpo(nombre = "Krillin",fuerza = 1,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Krillin",fuerza = 1,fuerzaTemp = 0)

  cartas1 += new CartaCuerpoCuerpo(nombre = "Link",fuerza = 3,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Link",fuerza = 3,fuerzaTemp = 0)

  cartas1 += new CartaCuerpoCuerpo(nombre = "Samus",fuerza = 4,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Samus",fuerza = 4,fuerzaTemp = 0)

  cartas1 += new CartaCuerpoCuerpo(nombre = "Ness",fuerza = 1,fuerzaTemp = 0)
  cartas2 += new CartaCuerpoCuerpo(nombre = "Ness",fuerza = 1,fuerzaTemp = 0)
  // Finalmente definimos 7 Cartas de Asedio
  cartas1 += new CartaAsedio(nombre = "Asediathor",fuerza = 6, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "Asediathor",fuerza = 6, fuerzaTemp = 0)
  
  cartas1 += new CartaAsedio(nombre = "Catapulta",fuerza = 5, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "Catapulta",fuerza = 5, fuerzaTemp = 0)
  
  cartas1 += new CartaAsedio(nombre = "Torre de Asedio",fuerza = 4, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "Torre de Asedio",fuerza = 4, fuerzaTemp = 0)
  
  cartas1 += new CartaAsedio(nombre = "Ariete",fuerza = 3, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "Ariete",fuerza = 3, fuerzaTemp = 0)
  
  cartas1 += new CartaAsedio(nombre = "Ballista",fuerza = 2, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "Ballista",fuerza = 2, fuerzaTemp = 0)
  
  cartas1 += new CartaAsedio(nombre = "SiegeTower",fuerza = 1, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "SiegeTower",fuerza = 1, fuerzaTemp = 0)
  
  cartas1 += new CartaAsedio(nombre = "Golem de Asedio",fuerza = 2, fuerzaTemp = 0)
  cartas2 += new CartaAsedio(nombre = "Golem de Asedio",fuerza = 2, fuerzaTemp = 0)
  
  // Definimos 4 Cartas de Clima
  cartas1 += new CartaClima(nombre = "Escarcha Mordiente")
  cartas2 += new CartaClima(nombre = "Escarcha Mordiente")
  
  cartas1 += new CartaClima(nombre = "Niebla Impenetrable")
  cartas2 += new CartaClima(nombre = "Niebla Impenetrable")
  
  cartas1 += new CartaClima(nombre = "Lluvia Torrencial")
  cartas2 += new CartaClima(nombre = "Lluvia Torrencial")
  
  cartas1 += new CartaClima(nombre = "Clima Despejado")
  cartas2 += new CartaClima(nombre = "Clima Despejado")

  val usuarioJuego: Usuario = context.usuarioJuego
  val cpuJuego: Cpu = context.cpuJuego

  override def doAction(): Unit = {
    // Setemaos el mazo del Usuario
    usuarioJuego.mazo = new Mazo(cartas1)
    // Ahora elegimos 10 cartas al azar para la mano del usuario con la funcion obtenerCartaAzar() de la clase Mazo
    for (i <- 1 to 10) {
      usuarioJuego.mano.addMember(context.usuarioJuego.mazo.obtenerCartaAzar())
    }

    // Setemaos el mazo del Cpu
    cpuJuego.mazo = new Mazo(cartas2)
    // Ahora elegimos 10 cartas al azar para la mano del cpu con la funcion obtenerCartaAzar() de la clase Mazo
    for (i <- 1 to 10) {
      cpuJuego.mano.addMember(context.cpuJuego.mazo.obtenerCartaAzar())
    }
    // Y Listo!, ya ambos jugadores tienen su mazo y su mano listos para jugar
  }
  
  // Solo falta definir el metodo para pasar al siguiente estado

  /**
   * Este metodo se encarga de pasar al siguiente estado, en este caso el estado de turno del usuario
   */
  override def TurnOfUSer(): Unit = {
    context.currentPlayer = usuarioJuego // Seteamos el jugador actual como el usuario
    context.state = new UserTurn(context)
  }

}
