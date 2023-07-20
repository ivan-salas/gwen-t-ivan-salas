package cl.uchile.dcc

import gwent.GameController
import gwent.PackageCartas.{CartaAsedio, CartaClima, CartaCuerpoCuerpo, CartaRango}
import gwent.PackageEstados.{CPUTurn, CardsShufNPick, GameState, InvalidTransitionException, PlayNextRound, PlayRound, StartGame, UserTurn}
import gwent.PackageJugador.{Cpu, Usuario}
import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}
import gwent.PackageTablero.ZonasJugadores.{ZonaCpu, ZonaUsuario}
import gwent.PackageTablero.{Tablero, ZonasJugadores}
import gwent.PackageView.{SimpleUserInterface, UserInterface}
import gwent.PackageTablero.ZonaClima.ZonaClima

import munit.FunSuite
import org.junit.Assert.assertThrows

import scala.collection.mutable.ArrayBuffer

class InvalidTransitionsTest extends FunSuite {
  var controller: GameController = _
  var testUserInterface: UserInterface = _

  override def beforeEach(context: BeforeEach): Unit = {
    testUserInterface = new SimpleUserInterface()
    controller = new GameController(testUserInterface)
  }

  test("1. Desde el estado inicial del juego no podemos ir a CardsShufNPick") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.GameBegins()
    }
  }

  test("2. Desde el estado inicial del juego no podemos ir a UserTurn") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.TurnOfUser()
    }
  }

  test("3. Desde el estado inicial del juego no podemos ir a Cpu turn") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.UserPassTurn()
    }
  }

  test("4. Desde el estado inicial del juego no podemos ir a GameEnds") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.OutOfGems()
    }
  }


  test("5. Desde el estado inicial no podemos hacer CpuPassTurn()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.CpuPassTurn()
    }
  }

  test("6. Desde el estado inicial no podemos hacer UserPassTurn()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.UserPassTurn()
    }
  }

  test("7. Desde el estado inicial no podemos hacer PlayNextRound()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.PlayNextRound()
    }
  }

  test("8. Desde el estado inicial no podemos hacer CpuPlayOneCard()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.CpuPlayOneCard()
    }
  }

  test("9. Desde el estado inicial no podemos hacer CpuPlayCardAgain()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.state.CpuPlayCardAgain()
    }
  }

  test("9. Desde el estado inicial no podemos hacer CpuPlayCardAgain()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.state.UserPassTurn()
    }
  }

  test("9. Desde el estado inicial no podemos hacer CpuPlayCardAgain()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.state.UserPlayCardAgain(3)
    }
  }

  test("9. Desde el estado inicial no podemos hacer CpuPlayCardAgain()") {
    // Use `intercept` to capture the exception and verify its type
    intercept[InvalidTransitionException] {
      controller.state.UserPlayOneCard(3)
    }
  }


}
