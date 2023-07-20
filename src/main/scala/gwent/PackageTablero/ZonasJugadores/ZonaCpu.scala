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
    //Checkear Refuerzo Moral: Cuando cierta carta entra en el campo, añade +1 a la fuerza de todas las cartas en su fila
    if (carta.nombre == "Bob Charlie") { //Esta es la única carta CuerpoCuerpo con dicho efecto
      this.filaCuerpoCuerpo.CartasFila.foreach(cartaFila => cartaFila.fuerzaTemp += 1)
    }
    //Checekar Vinculo estrecho: Si ya existe una carta con el mismo nombre en la fila, duplica la fuerza de la carta con vinculo estrecho,
    //incluyéndose a sí misma
    val nombreCarta = carta.nombre
    if (this.filaCuerpoCuerpo.CartasFila.exists(cartaFila => cartaFila.nombre == nombreCarta)) {
      this.filaCuerpoCuerpo.CartasFila.foreach(cartaFila => if (cartaFila.nombre == nombreCarta) cartaFila.fuerzaTemp = cartaFila.fuerza)
      // Falta añaidrle la fuerza temporal a la carta que vamos a agregar
      carta.fuerzaTemp = carta.fuerza
    }
    // Añadimos la carta a la fila
    this.filaCuerpoCuerpo.CartasFila.addOne(elem = carta)
  }

  /**
   * Permite jugar una carta de asedio en la zona de jugadores.
   *
   * @param carta La carta de asedio a jugar.
   */
  override def jugarCartaAsedio(carta: Carta): Unit = {
    //no necesitamos checkear refuerzo moral porque no hay cartas de asedio con dicho efecto
    //Checekar Vinculo estrecho: Si ya existe una carta con el mismo nombre en la fila, duplica la fuerza de la carta con vinculo estrecho,
    //incluyéndose a sí misma
    val nombreCarta = carta.nombre
    if (this.filaAsedio.CartasFila.exists(cartaFila => cartaFila.nombre == nombreCarta)) {
      this.filaAsedio.CartasFila.foreach(cartaFila => if (cartaFila.nombre == nombreCarta) cartaFila.fuerzaTemp = cartaFila.fuerza)
      // Falta añaidrle la fuerza temporal a la carta que vamos a agregar
      carta.fuerzaTemp = carta.fuerza
    }
    this.filaAsedio.CartasFila.addOne(elem = carta)
  }

  /**
   * Permite jugar una carta de rango en la zona de jugadores.
   *
   * @param carta La carta de rango a jugar.
   */
  override def jugarCartaRango(carta: Carta): Unit = {
    //no necesitamos chequear refuerzo moral porque no hay cartas de rango con dicho efecto
    //Checekar Vinculo estrecho: Si ya existe una carta con el mismo nombre en la fila, duplica la fuerza de la carta con vinculo estrecho,
    //incluyéndose a sí misma
    val nombreCarta = carta.nombre
    if (this.filaRango.CartasFila.exists(cartaFila => cartaFila.nombre == nombreCarta)) {
      this.filaRango.CartasFila.foreach(cartaFila => if (cartaFila.nombre == nombreCarta) cartaFila.fuerzaTemp = cartaFila.fuerza)
      // Falta añaidrle la fuerza temporal a la carta que vamos a agregar
      carta.fuerzaTemp = carta.fuerza
    }
    this.filaRango.CartasFila.addOne(elem = carta)
  }
  
  
}
