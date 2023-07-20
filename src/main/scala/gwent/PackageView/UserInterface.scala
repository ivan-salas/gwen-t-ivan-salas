package cl.uchile.dcc
package gwent.PackageView

import scala.collection.mutable.ArrayBuffer

/** Un rasgo que representa una interfaz de usuario para el juego.
 *
 * UserInterface define un método abstracto, promptSelection, que está diseñado para interactuar con
 * el usuario y recopilar su selección de una lista dada de opciones.
 * Las clases concretas que implementan este rasgo deben proporcionar su propia implementación del
 * método promptSelection, permitiendo que el juego se juegue a través de diferentes tipos de
 * interfaces de usuario (por ejemplo, línea de comandos, gráficas, basadas en web, etc.).
*/
trait UserInterface {
  /** Solicita al usuario hacer una selección de una lista dada de opciones.
   *
   *
   * Las clases que lo implementen deben proporcionar un método que presente las opciones al usuario y
   * recoja su elección, devolviendo el índice de la opción seleccionada.
   * @param options Una lista de Strings que representan las opciones disponibles para la selección.
   * @return El índice de la opción seleccionada por el usuario en la lista.
   */
  def promptSelection(options: ArrayBuffer[String]): Int

}
