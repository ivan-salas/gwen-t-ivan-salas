package cl.uchile.dcc
package gwent.PackageView

import scala.collection.mutable.ArrayBuffer

class SimpleUserInterface extends UserInterface{
  
  override def promptSelection(options: ArrayBuffer[String]): Int = {
    println("Please select an option:")
    for (i <- options.indices) {
      println(s"$i: ${options(i)}")
    }
    scala.io.StdIn.readInt()
  }

}
