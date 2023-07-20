package cl.uchile.dcc

import gwent.GameController
import gwent.PackageCartas.{CartaAsedio, CartaClima, CartaCuerpoCuerpo, CartaRango}
import gwent.PackageEstados.{CPUTurn, CardsShufNPick, GameEnds, PlayNextRound, PlayRound, StartGame, UserTurn}
import gwent.PackageJugador.{Cpu, Usuario}
import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import gwent.PackageTablero.ZonasJugadores.{ZonaCpu, ZonaUsuario}
import gwent.PackageTablero.{Tablero, ZonasJugadores}
import gwent.PackageView.{SimpleUserInterface, UserInterface}
import gwent.PackageTablero.ZonaClima.ZonaClima

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class ControllerTest extends FunSuite {
  var controller: GameController = _
  var testUserInterface: UserInterface = _

  override def beforeEach(context: BeforeEach): Unit = {
    testUserInterface = new SimpleUserInterface()
    controller = new GameController(testUserInterface)
  }

  test("1. El estado inicial del juego debe ser StartGame") {
    assertEquals(controller.getState.getClass, classOf[StartGame])
  }

  test("2. El juego debe avanzar desde StartGame a PlayRound") {
    controller.StartARound()
    assertEquals(controller.getState.getClass, classOf[PlayRound])
  }

  test("3. El juego debe avanzar desde PlayRound a CardsShufNPick") {
    controller.StartARound()
    controller.GameBegins()

    // Verificamos que el estado actual del controlador sea una instancia de CardsShufNPick
    assertEquals(
      controller.state.getClass, // Obtenemos la clase real del estado
      classOf[CardsShufNPick]    // Obtenemos la referencia a la clase esperada
    )
    // La aserción anterior es válida y no rompe los principios de la programación orientada a objetos (OOP).
    // Estamos comprobando la clase real del estado en tiempo de ejecución para asegurarnos de que coincida con
    // la clase esperada CardsShufNPick. Esto no implica una violación de la OOP, ya que estamos utilizando las
    // características proporcionadas por el lenguaje para comparar clases en tiempo de ejecución.
  }

  test("4. El juego debe avanzar desde CardsShufNPick a UserTurn") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    assertEquals(controller.state.getClass, classOf[UserTurn])
  }

  test("5. El juego debe avanzar desde UserTurn a CpuTurn") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    controller.UserPassTurn()
    assertEquals(controller.currentPlayer, controller.cpuJuego)
    assertEquals(controller.state.getClass, classOf[CPUTurn])

  }

  test("6. El jugador de usuario debe jugar una carta") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    controller.state.UserPlayOneCard(3)
    // Agrega las verificaciones específicas aquí

    // Primero chequeamos que la carta se haya eliminado de la mano del jugador
    // Originalmente tenia 10 cartas, ahora deberia tener 9
    assertEquals(controller.usuarioJuego.mano.CartasMembers.length, 9)

    // Luego chequeamos que haya 1 carta en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(),1)
  }

  test("7. La Cpu debe jugar una carta") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    controller.state.UserPlayOneCard(3)
    controller.CpuPlayOneCard()

    // Primero chequeamos que la carta se haya eliminado de la mano de la CPU
    // Originalmente tenia 10 cartas, ahora deberia tener 9
    assertEquals(controller.cpuJuego.mano.CartasMembers.length, 9)

    // Luego chequeamos que haya 2 cartas en el tablero (una del usuario y otra de la CPU)
    assertEquals(controller.tableroJuego.NumCartasTablero(),2)
  }

  test("8. El jugador de CPU debe pasar el turno") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    controller.state.UserPlayOneCard(3)
    controller.CpuPassTurn()

    // Luego de que Cpu pasa el turno, el jugador actual debe ser el usuario
    assertEquals(controller.currentPlayer, controller.usuarioJuego)

    // Ahora tambien debiesemos ser capaces de invocar UserPlayCardAgain
    controller.state.UserPlayCardAgain(3)

    // Y el turno debiese seguir siendo del usuario
    assertEquals(controller.currentPlayer, controller.usuarioJuego)
  }

  test("9. Despues de 8 turnos el juego debe terminar") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    controller.state.UserPlayOneCard(9) // -1 turno: quedan 7 turnos
    controller.CpuPassTurn()
    controller.state.UserPlayOneCard(8) // -1 turno: quedan 6 turnos
    controller.CpuPlayOneCard() // -1 turno: quedan 5 turnos
    controller.state.UserPlayOneCard(7) // -1 turno: quedan 4 turnos
    controller.CpuPlayOneCard() // -1 turno: quedan 3 turnos
    controller.state.UserPlayOneCard(6) // -1 turno: quedan 2 turnos
    controller.CpuPlayOneCard() // -1 turno: queda 1 turno
    controller.state.UserPlayOneCard(5) // -1 turno: queda 0 turno

    controller.state.doAction() // el observador de turnos debiese actuar ahora y llevarnos a PlayNextRound
    // Ahora el juego debiese estar en estado PlayNextRound
    assertEquals(controller.state.getClass, classOf[PlayNextRound])

    // avanzemos, desde el estado PlayNextRound, hasta el estado UserTurn
    controller.TurnOfUser()
    assertEquals(controller.state.getClass, classOf[UserTurn])
    controller.UserPassTurn()
    assertEquals(controller.state.getClass, classOf[CPUTurn])
    controller.CpuPlayCardAgain()
    assertEquals(controller.state.getClass, classOf[CPUTurn])
  }

  /*Tests para efectos de cartas*/
  test("10. efectos de las cartas, Refuerzo moral para usuario)"){
    //Primero vamos a testear que refuerzo moral funcione
    //Para eso juguemos un par de cartas en el tablero
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    // En este caso queremos jugar un par de cartas cuerpo a cuerpo
    // Porque la carta que tiene refuerzo moral es de cuerpo a cuerpo
    val carta1 = new CartaCuerpoCuerpo(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    val carta2 = new CartaCuerpoCuerpo(nombre = "Super Macho Man", fuerza = 3, fuerzaTemp = 0)
    val carta3 = new CartaCuerpoCuerpo(nombre = "Krillin", fuerza = 1, fuerzaTemp = 0)
    val carta4 = new CartaCuerpoCuerpo(nombre = "Link", fuerza = 3, fuerzaTemp = 0)
    val carta5 = new CartaCuerpoCuerpo(nombre = "Samus", fuerza = 4, fuerzaTemp = 0)
    val carta6 = new CartaCuerpoCuerpo(nombre = "Ness", fuerza = 1, fuerzaTemp = 0)
    // La suma de fuerza hasta ahora debiese ser 16

    // Creamos la carta con refuerzo moral
    val carta7 = new CartaCuerpoCuerpo(nombre = "Bob Charlie", fuerza = 5, fuerzaTemp = 0)

    // Ahora juguemos las cartas
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta1)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta2)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta3)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta4)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta5)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta6)

    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(),6)
    //Chequeemos que la fuerza hasta ahora sea solo la suma de fuerza normal de las cartas
    var sumaFuerzaUsuario = controller.tableroJuego.zonaUsuario.sumaFuerza()
    assertEquals(sumaFuerzaUsuario,16)

    //Ahora juguemos la carta con refuerzo moral
    controller.usuarioJuego.jugarCarta(controller.tableroJuego,carta7)
    //Ahora la fuerza debiese ser (16+5) + 6 por el efecto = 27
    sumaFuerzaUsuario = controller.tableroJuego.zonaUsuario.sumaFuerza()
    assertEquals(sumaFuerzaUsuario,27)
  }

  test("11. Efectos de cartas, vinculo estrecho CuerpoCuerpo para usuario") {
    //Testeamos que vinculo estrecho funcione
    //Para eso juguemos la misma carta varias veces
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    val carta1 = new CartaCuerpoCuerpo(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    // Ahora juguemos las cartas
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 2)

    // Nominalmente deberian tener una fuerza de 4+4=8, pero por el vinculo estrecho se duplica y debiese ser 16
    assertEquals(controller.tableroJuego.zonaUsuario.sumaFuerza(), 16)

  }

  test("12. Efectos de cartas, vinculo estrecho Rango para usuario") {
    //Testeamos que vinculo estrecho funcione
    //Para eso juguemos la misma carta varias veces
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    val carta1 = new CartaRango(nombre = "Rangeador", fuerza = 4, fuerzaTemp = 0)
    // Ahora juguemos las cartas
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 2)

    // Nominalmente deberian tener una fuerza de 4+4=8, pero por el vinculo estrecho se duplica y debiese ser 16
    assertEquals(controller.tableroJuego.zonaUsuario.sumaFuerza(), 16)

  }

  test("13. Efectos de cartas, vinculo estrecho Asedio para usuario") {
    //Testeamos que vinculo estrecho funcione
    //Para eso juguemos la misma carta varias veces
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    val carta1 = new CartaAsedio(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    // Ahora juguemos las cartas
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 2)

    // Nominalmente deberian tener una fuerza de 4+4=8, pero por el vinculo estrecho se duplica y debiese ser 16
    assertEquals(controller.tableroJuego.zonaUsuario.sumaFuerza(), 16)

  }

  test("14. efectos de las cartas, Refuerzo moral para CPU)") {
    //Primero vamos a testear que refuerzo moral funcione
    //Para eso juguemos un par de cartas en el tablero
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    // En este caso queremos jugar un par de cartas cuerpo a cuerpo
    // Porque la carta que tiene refuerzo moral es de cuerpo a cuerpo
    val carta1 = new CartaCuerpoCuerpo(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    val carta2 = new CartaCuerpoCuerpo(nombre = "Super Macho Man", fuerza = 3, fuerzaTemp = 0)
    val carta3 = new CartaCuerpoCuerpo(nombre = "Krillin", fuerza = 1, fuerzaTemp = 0)
    val carta4 = new CartaCuerpoCuerpo(nombre = "Link", fuerza = 3, fuerzaTemp = 0)
    val carta5 = new CartaCuerpoCuerpo(nombre = "Samus", fuerza = 4, fuerzaTemp = 0)
    val carta6 = new CartaCuerpoCuerpo(nombre = "Ness", fuerza = 1, fuerzaTemp = 0)
    // La suma de fuerza hasta ahora debiese ser 16

    // Creamos la carta con refuerzo moral
    val carta7 = new CartaCuerpoCuerpo(nombre = "Bob Charlie", fuerza = 5, fuerzaTemp = 0)

    // Ahora juguemos las cartas
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta2)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta3)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta4)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta5)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta6)

    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 6)
    //Chequeemos que la fuerza hasta ahora sea solo la suma de fuerza normal de las cartas
    var sumaFuerzaCpu = controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(sumaFuerzaCpu, 16)

    //Ahora juguemos la carta con refuerzo moral
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta7)
    //Ahora la fuerza debiese ser (16+5) + 6 por el efecto = 27
    sumaFuerzaCpu = controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(sumaFuerzaCpu, 27)
  }

  test("15. Efectos de cartas, vinculo estrecho CuerpoCuerpo para CPU") {
    //Testeamos que vinculo estrecho funcione
    //Para eso juguemos la misma carta varias veces
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    val carta1 = new CartaCuerpoCuerpo(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    // Ahora juguemos las cartas
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 2)

    // Nominalmente deberian tener una fuerza de 4+4=8, pero por el vinculo estrecho se duplica y debiese ser 16
    assertEquals(controller.tableroJuego.zonaCpu.sumaFuerza(), 16)

  }

  test("15. Efectos de cartas, vinculo estrecho Rango para CPU") {
    //Testeamos que vinculo estrecho funcione
    //Para eso juguemos la misma carta varias veces
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    val carta1 = new CartaRango(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    // Ahora juguemos las cartas
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 2)

    // Nominalmente deberian tener una fuerza de 4+4=8, pero por el vinculo estrecho se duplica y debiese ser 16
    assertEquals(controller.tableroJuego.zonaCpu.sumaFuerza(), 16)

  }

  test("15. Efectos de cartas, vinculo estrecho Asedio para CPU") {
    //Testeamos que vinculo estrecho funcione
    //Para eso juguemos la misma carta varias veces
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Solo para efectos del test vamos a "forzar" las cartas que queremos jugar
    val carta1 = new CartaAsedio(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    // Ahora juguemos las cartas
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Chequeemos que hayan 6 cartas en el tablero
    assertEquals(controller.tableroJuego.NumCartasTablero(), 2)

    // Nominalmente deberian tener una fuerza de 4+4=8, pero por el vinculo estrecho se duplica y debiese ser 16
    assertEquals(controller.tableroJuego.zonaCpu.sumaFuerza(), 16)

  }

  test("16. Efecto Escarcha Mordiente"){
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    //De nuevo, para efectos del test forzamos la carta a jugar
    val carta1 = new CartaCuerpoCuerpo(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Hasta ahora ambos tienen una carta cuerpo cuerpo de fuerza igual a 4
    // La fuerza total debiese ser 8
    var fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 8)

    val carta2 = new CartaClima(nombre = "Escarcha Mordiente")
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta2)

    //Ahora la fuerza del tablero debiese sumar 2 y no 8
    fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 2)
  }

  test("17. Efecto Niebla Impenetrable") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    //De nuevo, para efectos del test forzamos la carta a jugar
    val carta1 = new CartaRango(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Hasta ahora ambos tienen una carta cuerpo cuerpo de fuerza igual a 4
    // La fuerza total debiese ser 8
    var fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 8)

    val carta2 = new CartaClima(nombre = "Niebla Impenetrable")
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta2)

    //Ahora la fuerza del tablero debiese sumar 2 y no 8
    fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 2)
  }

  test("18. Efecto Lluvia Torrencial") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    //De nuevo, para efectos del test forzamos la carta a jugar
    val carta1 = new CartaAsedio(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Hasta ahora ambos tienen una carta cuerpo cuerpo de fuerza igual a 4
    // La fuerza total debiese ser 8
    var fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 8)

    val carta2 = new CartaClima(nombre = "Lluvia Torrencial")
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta2)

    //Ahora la fuerza del tablero debiese sumar 2 y no 8
    fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 2)
  }

  test("19. Efecto Clima Despejado") {
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()
    //De nuevo, para efectos del test forzamos la carta a jugar
    val carta1 = new CartaAsedio(nombre = "Dragon Chan", fuerza = 4, fuerzaTemp = 0)
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta1)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, carta1)
    //Hasta ahora ambos tienen una carta cuerpo cuerpo de fuerza igual a 4
    // La fuerza total debiese ser 8
    var fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 8)

    val carta2 = new CartaClima(nombre = "Lluvia Torrencial")
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta2)

    //Ahora la fuerza del tablero debiese sumar 2 y no 8
    fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 2)

    val carta3 = new CartaClima(nombre = "Clima Despejado")
    //Ahora al jugar clima despejado la fuerza va a volver a ser 8
    controller.usuarioJuego.jugarCarta(controller.tableroJuego, carta3)
    fuerzaTotal = controller.tableroJuego.zonaUsuario.sumaFuerza() + controller.tableroJuego.zonaCpu.sumaFuerza()
    assertEquals(fuerzaTotal, 8)
  }

  test("20. Chequear que se termina un juego"){
    //Como es medio aleatorio quien gana y quien pierde, vamos a forzar que el usuario juegue cartas malas
    //y el cpu cartas buenas
    controller.StartARound()
    controller.GameBegins()
    controller.TurnOfUser()

    // Al forzar jugar esta carta nos aseguramos de que CPU gane
    val SuperCarta = new CartaCuerpoCuerpo(nombre = "SuperCarta", fuerza = 9999, fuerzaTemp = 0)
    controller.cpuJuego.jugarCarta(controller.tableroJuego, SuperCarta)

    controller.state.UserPlayOneCard(9) // -1 turno: quedan 7 turnos
    controller.CpuPassTurn()
    controller.state.UserPlayOneCard(8) // -1 turno: quedan 6 turnos
    controller.CpuPlayOneCard() // -1 turno: quedan 5 turnos
    controller.state.UserPlayOneCard(7) // -1 turno: quedan 4 turnos
    controller.CpuPlayOneCard() // -1 turno: quedan 3 turnos
    controller.state.UserPlayOneCard(6) // -1 turno: quedan 2 turnos
    controller.CpuPlayOneCard() // -1 turno: queda 1 turno
    controller.state.UserPlayOneCard(5) // -1 turno: queda 0 turno

    controller.state.doAction() // el observador de turnos debiese actuar ahora y llevarnos a PlayNextRound
    // Ahora el juego debiese estar en estado PlayNextRound
    assertEquals(controller.state.getClass, classOf[PlayNextRound])

    // avanzemos, desde el estado PlayNextRound, hasta el estado UserTurn
    controller.TurnOfUser()
    assertEquals(controller.state.getClass, classOf[UserTurn])
    controller.UserPassTurn()
    assertEquals(controller.state.getClass, classOf[CPUTurn])
    controller.CpuPlayCardAgain() // -1 turno: quedan 7 turnos
    assertEquals(controller.state.getClass, classOf[CPUTurn])
    controller.CpuPlayCardAgain() // -1 turno: quedan 6 turnos
    controller.CpuPlayCardAgain() // -1 turno: quedan 5 turnos
    controller.CpuPlayCardAgain() // -1 turno: quedan 4 turnos
    controller.CpuPlayCardAgain() // -1 turno: quedan 3 turnos
    controller.CpuPlayCardAgain() // -1 turno: quedan 2 turnos
    controller.CpuPlayCardAgain() // -1 turno: quedan 1 turno
    controller.CpuPlayCardAgain() // -1 turno: quedan 0 turno
    //Al quedar 0 turnos nos movemos a PlayNextRound
    //PlayNextRound revisa que usuario pierde y que se queda con 0 gemas
    // Llamamos al doAction() de PlayNextRound
    controller.doAction() //Aqui el gem observer nos lleva GameEnds
    //Finalmente el estado del juego debiese ser GameEnds
    assertEquals(controller.state.getClass, classOf[GameEnds])

  }

}

