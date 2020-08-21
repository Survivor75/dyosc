package com.language

object trait_2 {

  //  Create Trait With Type Parameters
  trait chocalateShoppingCartDao[A] {

    def add(chocalate: A): Long

    def update(chocalate: A): Boolean

    def search(chocalate: A): A

    def delete(chocalate: A): Boolean

  }

  //  Extend Multiple Traits
  trait chocalateInventoryService[A] {

    def checkStockQuantity(chocalate: A): Int

  }

  class chocalateShoppingCart[A] extends chocalateShoppingCartDao[A] with chocalateInventoryService[A] {

    override def add(chocalate: A): Long = {
      println(s"chocalateShoppingCart-> add method -> chocalate: $chocalate")
      1
    }

    override def update(chocalate: A): Boolean = {
      println(s"chocalateShoppingCart-> update method -> chocalate: $chocalate")
      true
    }

    override def search(chocalate: A): A = {
      println(s"chocalateShoppingCart-> search method -> chocalate: $chocalate")
      chocalate
    }

    override def delete(chocalate: A): Boolean = {
      println(s"chocalateShoppingCart-> delete method -> chocalate: $chocalate")
      true
    }

    override def checkStockQuantity(chocalate: A): Int = {
      println(s"chocalateShoppingCart-> checkStockQuantity method -> chocalate: $chocalate")
      10
    }
  }

  val chocalateShoppingCart: chocalateShoppingCart[String] = new chocalateShoppingCart[String]()
  chocalateShoppingCart.add("Vanilla chocalate")
  chocalateShoppingCart.update("Vanilla chocalate")
  chocalateShoppingCart.search("Vanilla chocalate")
  chocalateShoppingCart.delete("Vanilla chocalate")

  val chocalateShoppingCart2: chocalateShoppingCartDao[String] = new chocalateShoppingCart[String]()
  chocalateShoppingCart2.add("Vanilla chocalate")
  chocalateShoppingCart2.update("Vanilla chocalate")
  chocalateShoppingCart2.search("Vanilla chocalate")
  chocalateShoppingCart2.delete("Vanilla chocalate")

  chocalateShoppingCart.checkStockQuantity("Vanilla chocalate")

}
