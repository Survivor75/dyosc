package com.language

object classes_3 {

  //  Implicit Class - Extension Methods
  case class Donut(name: String, price: Double, productCode: Option[Long] = None)

  val vanillaDonut: Donut = Donut("Vanilla", 1.50)

  println(s"Vanilla donut name = ${vanillaDonut.name}")
  println(s"Vanilla donut price = ${vanillaDonut.price}")
  println(s"Vanilla donut produceCode = ${vanillaDonut.productCode}")

  object DonutImplicits {

    implicit class AugmentedDonut(donut: Donut) {
      def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
    }

  }

  import DonutImplicits._
  println(s"Vanilla donut uuid = ${vanillaDonut.uuid}")

}
