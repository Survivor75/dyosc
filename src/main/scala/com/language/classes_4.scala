package com.language

class classes_4 {

  //  Extend Class - Class Inheritance
  abstract class chocalate(name: String) {

    def printName: Unit

  }

  class Vanillachocalate(name: String) extends chocalate(name) {

    override def printName: Unit = println(name)

  }

  object Vanillachocalate {

    def apply(name: String): chocalate = {
      new Vanillachocalate(name)
    }

  }

  class Glazedchocalate(name: String) extends chocalate(name) {

    override def printName: Unit = println(name)

  }

  object Glazedchocalate {

    def apply(name: String): chocalate = {
      new Glazedchocalate(name)
    }

  }

  val vanillachocalate: chocalate = Vanillachocalate("Vanilla chocalate")
  vanillachocalate.printName

  val glazedchocalate: chocalate = Glazedchocalate("Glazed chocalate")
  glazedchocalate.printName


  //  Extend Case Class - Case Class Inheritance
  case class VanillachocalateCase(name: String) extends chocalate(name) {

    override def printName: Unit = println(name)

  }

  case class GlazedchocalateCase(name: String) extends chocalate(name) {

    override def printName: Unit = println(name)

  }


  //  Create Typed Class
  class ShoppingCart[D <: chocalate](chocalates: Seq[D]) {

    def printCartItems: Unit = chocalates.foreach(_.printName)

  }

  val shoppingCart: ShoppingCart[chocalate] = new ShoppingCart(Seq[chocalate](vanillachocalate, glazedchocalate))
  shoppingCart.printCartItems

}
