package cl.uchile.dcc
package gwent.PackageTablero.ZonasJugadores

import gwent.PackageTablero.ZonasJugadores.SubZonas.{FilaAsedio, FilaCuerpoCuerpo, FilaRango}

import cl.uchile.dcc.gwent.PackageCartas.Carta

/**
 * Clase que representa la zona de la CPU en el tablero de juego.
 *
 * @param filaRango        Fila de cartas de rango de la zona de la CPU.
 * @param filaCuerpoCuerpo Fila de cartas cuerpo a cuerpo de la zona de la CPU.
 * @param filaAsedio       Fila de cartas de asedio de la zona de la CPU.
 */
class ZonaCpu(filaRango: FilaRango, filaCuerpoCuerpo: FilaCuerpoCuerpo, filaAsedio: FilaAsedio) 
  extends AbstractZonaPlayers(filaRango, filaCuerpoCuerpo, filaAsedio) {

  override def jugarCartaCuerpoCuerpo(carta: Carta): Unit = {
    this.filaCuerpoCuerpo.CartasFila.addOne(elem = carta)
  }

  override def jugarCartaAsedio(carta: Carta): Unit = {
    this.filaAsedio.CartasFila.addOne(elem = carta)
  }

  override def jugarCartaRango(carta: Carta): Unit = {
    this.filaRango.CartasFila.addOne(elem = carta)
  }
}
