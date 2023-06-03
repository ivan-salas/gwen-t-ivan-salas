package cl.uchile.dcc
package gwent.PackageJugador

import gwent.PackageBarajas.{Mano, Mazo}

import cl.uchile.dcc.gwent.PackageCartas.{AbstractCarta, Carta}
import cl.uchile.dcc.gwent.PackageTablero.Tablero
import cl.uchile.dcc.gwent.PackageTablero.ZonasJugadores.{AbstractZonaPlayers, TraitZona}

import scala.annotation.unused


/**
 * Clase abstracta que define a un jugador (Cpu o Usuario), con sus gemas, mazo y mano.
 * @param gemas Cantidad de gemas
 * @param mazo Mazo de cartas del jugador
 * @param mano  Mano de cartas del jugador
 */
abstract class AbstractJugador(private var gemas: Int, private var mazo: Mazo, private var mano: Mano) extends Jugador with Equals{

// De nuevo se definen equals y tostring
  override def equals(that: Any): Boolean = {
    if (canEqual(that)) { // Preguntamos si el obj que estamos comparando es del mismo tipo de esta
      // clase
      val other = that.asInstanceOf[AbstractJugador] // Si lo es, realizamos un casteo para obtener las propiedades de
      // dicha clase y poder compararlas.
      (this eq other) || (gemas==other.gemas) // retornamos lo que dé este resultado, si es que son iguales
      // referencialmente estamos listos, o que sus parámetros sean iguales.
    } else { // Chao, no son iguales.
      false // retornamos falso de una.
    }
  }

  override def toString: String = { // No se confundan con getClass.getName,
    // solo consideren que entrega el nombre de la clase.
    s"""Class: ${getClass.getName}
       |gemas: $gemas""".stripMargin // stripMargin se coloca automáticamente cuando
    // queremos hacer un String de más de una línea.

  }

  def robarCartaDeMazo(): Unit = {
    mano.addMember(mazo.obtenerCarta()) //Tambien se define la funcion robarCarta, que roba una carta del mazo y la añade a la mano
  }

  def sacarCartaDeMano(nombre: String): AbstractCarta = {
    mano.sacarCarta(nombre) // Se define la funcion SacarCartaDeMano, que saca una carta de la mano
    //Quizás en un futuro se haga un override de esta funcion para los subcasos especificos dentro de CPU
    //Como por ejemplo que para CPU saque una carta al azar de la mano y no se guie por el nombre
  }

  /**
   * Funcion getter del mazo
   * @return mazo
   */
  def getMazo: Mazo = {
    mazo
  }
  
  /**
   * Funcion getter de la mano
   * @return la mano del jugador
   */
  def getMano: Mano = {
    mano
  }

  /**
   * Funcion getter del numero de gemas del jugador
   * @return numero de gemas
   */
  def getGemas: Int = {
    gemas
  }

  /**
   * Funcion Setter del numero de gemas del jugador.
   * Recibe un numero de gemas y le asigna ese valor al jugador.
   * El valor debe ser positivo, si no, lanza una excepcion.
   * @param int Numero de gemas
   * @throws IllegalArgumentException Si el numero de gemas es negativo
   */
  def setGemas(int: Int): Unit = {
    if (int < 0) {
      throw new IllegalArgumentException("Las gemas no pueden ser negativas")
    }
    else gemas = int
  }






}
