package cl.uchile.dcc
package TestTablero.TestZonas

import gwent.PackageTablero.ZonaClima.ZonaClima
import gwent.PackageCartas.CartaClima

class ZonaClimaTest extends munit.FunSuite{
  var ZonaClima1: ZonaClima = _
  var ZonaClima2: ZonaClima = _

  override def beforeEach(context: BeforeEach): Unit = {
    val CartaClima1 = new CartaClima("Lluvia")
    val ZonaClima1 = new ZonaClima(cartaClima = Some(CartaClima1))
  }


    //La zona clima solo debe tener una carta clima
    test("La zona clima solo debe tener una carta clima") {
      val cartaclima = new CartaClima("Lluvia")
      val ZonaClima1 = new ZonaClima(cartaClima = Some(cartaclima))
      assert(ZonaClima1.cartaClima.orNull.getClass == cartaclima.getClass)
    }

}
