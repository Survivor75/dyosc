package com.language

object trait_1 {

  //  Create And Extend Trait In Scala
  trait chocalateShoppingCartDao {

    def add(chocalateName: String): Long

    def update(chocalateName: String): Boolean

    def search(chocalateName: String): String

    def delete(chocalateName: String): Boolean

  }

  class chocalateShoppingCart extends chocalateShoppingCartDao {

    override def add(chocalateName: String): Long = {
      println(s"chocalateShoppingCart-> add method -> chocalateName: $chocalateName")
      1
    }

    override def update(chocalateName: String): Boolean = {
      println(s"chocalateShoppingCart-> update method -> chocalateName: $chocalateName")
      true
    }

    override def search(chocalateName: String): String = {
      println(s"chocalateShoppingCart-> search method -> chocalateName: $chocalateName")
      chocalateName
    }

    override def delete(chocalateName: String): Boolean = {
      println(s"chocalateShoppingCart-> delete method -> chocalateName: $chocalateName")
      true
    }
  }

  val chocalateShoppingCart1: chocalateShoppingCart = new chocalateShoppingCart()
  chocalateShoppingCart1.add("Vanilla chocalate")
  chocalateShoppingCart1.update("Vanilla chocalate")
  chocalateShoppingCart1.search("Vanilla chocalate")
  chocalateShoppingCart1.delete("Vanilla chocalate")


  val chocalateShoppingCart2: chocalateShoppingCartDao = new chocalateShoppingCart()
  chocalateShoppingCart2.add("Vanilla chocalate")
  chocalateShoppingCart2.update("Vanilla chocalate")
  chocalateShoppingCart2.search("Vanilla chocalate")
  chocalateShoppingCart2.delete("Vanilla chocalate")

}
