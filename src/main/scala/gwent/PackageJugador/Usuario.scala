package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageCartas.Carta
class Usuario(val nombre: String, var gemas: Int, var mazo: List[Carta], var mano: List[Carta]) extends Jugador{
  def ObtenerNumeroCartasMazo(): Int = mazo.length
  def ObtenerNumeroCartasMano(): Int = mano.length

}
