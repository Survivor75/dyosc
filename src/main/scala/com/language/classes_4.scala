package com.language

class classes_4 {

  //  Extend Class - Class Inheritance
  abstract class Donut(name: String) {

    def printName: Unit

  }

  class VanillaDonut(name: String) extends Donut(name) {

    override def printName: Unit = println(name)

  }

  object VanillaDonut {

    def apply(name: String): Donut = {
      new VanillaDonut(name)
    }

  }

  class GlazedDonut(name: String) extends Donut(name) {

    override def printName: Unit = println(name)

  }

  object GlazedDonut {

    def apply(name: String): Donut = {
      new GlazedDonut(name)
    }

  }

  val vanillaDonut: Donut = VanillaDonut("Vanilla Donut")
  vanillaDonut.printName

  val glazedDonut: Donut = GlazedDonut("Glazed Donut")
  glazedDonut.printName


  //  Extend Case Class - Case Class Inheritance
  case class VanillaDonutCase(name: String) extends Donut(name) {

    override def printName: Unit = println(name)

  }

  case class GlazedDonutCase(name: String) extends Donut(name) {

    override def printName: Unit = println(name)

  }


  //  Create Typed Class
  class ShoppingCart[D <: Donut](donuts: Seq[D]) {

    def printCartItems: Unit = donuts.foreach(_.printName)

  }

  val shoppingCart: ShoppingCart[Donut] = new ShoppingCart(Seq[Donut](vanillaDonut, glazedDonut))
  shoppingCart.printCartItems

}
