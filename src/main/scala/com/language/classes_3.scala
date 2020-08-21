package com.language

object classes_3 {

  //  Implicit Class - Extension Methods
  case class chocalate(name: String, price: Double, productCode: Option[Long] = None)

  val vanillachocalate: chocalate = chocalate("Vanilla", 1.50)

  println(s"Vanilla chocalate name = ${vanillachocalate.name}")
  println(s"Vanilla chocalate price = ${vanillachocalate.price}")
  println(s"Vanilla chocalate produceCode = ${vanillachocalate.productCode}")

  object chocalateImplicits {

    implicit class Augmentedchocalate(chocalate: chocalate) {
      def uuid: String = s"${chocalate.name} - ${chocalate.productCode.getOrElse(12345)}"
    }

  }

  import chocalateImplicits._
  println(s"Vanilla chocalate uuid = ${vanillachocalate.uuid}")

}
