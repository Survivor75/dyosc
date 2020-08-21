package com.language

class trait_3 {

  //  Traits For Dependency Injection
  trait chocalateDatabase[A] {

    def addOrUpdate(chocalate: A): Long

    def query(chocalate: A): A

    def delete(chocalate: A): Boolean
  }

  class CassandrachocalateStore[A] extends chocalateDatabase[A] {

    override def addOrUpdate(chocalate: A): Long = {
      println(s"CassandrachocalateDatabase-> addOrUpdate method -> chocalate: $chocalate")
      1
    }

    override def query(chocalate: A): A = {
      println(s"CassandrachocalateDatabase-> query method -> chocalate: $chocalate")
      chocalate
    }

    override def delete(chocalate: A): Boolean = {
      println(s"CassandrachocalateDatabase-> delete method -> chocalate: $chocalate")
      true
    }
  }

  trait chocalateShoppingCartDao[A] {

    val chocalateDatabase: chocalateDatabase[A] // dependency injection

    def add(chocalate: A): Long = {
      println(s"chocalateShoppingCartDao-> add method -> chocalate: $chocalate")
      chocalateDatabase.addOrUpdate(chocalate)
    }

    def update(chocalate: A): Boolean = {
      println(s"chocalateShoppingCartDao-> update method -> chocalate: $chocalate")
      chocalateDatabase.addOrUpdate(chocalate)
      true
    }

    def search(chocalate: A): A = {
      println(s"chocalateShoppingCartDao-> search method -> chocalate: $chocalate")
      chocalateDatabase.query(chocalate)
    }

    def delete(chocalate: A): Boolean = {
      println(s"chocalateShoppingCartDao-> delete method -> chocalate: $chocalate")
      chocalateDatabase.delete(chocalate)
    }

  }

  trait chocalateInventoryService[A] {

    val chocalateDatabase: chocalateDatabase[A] // dependency injection

    def checkStockQuantity(chocalate: A): Int = {
      println(s"chocalateInventoryService-> checkStockQuantity method -> chocalate: $chocalate")
      chocalateDatabase.query(chocalate)
      1
    }

  }

  trait chocalateShoppingCartServices[A] extends chocalateShoppingCartDao[A] with chocalateInventoryService[A] {
    override val chocalateDatabase: chocalateDatabase[A] = new CassandrachocalateStore[A]()
  }

  class chocalateShoppingCart[A] extends chocalateShoppingCartServices[A] {

  }

  val chocalateShoppingCart: chocalateShoppingCart[String] = new chocalateShoppingCart[String]()
  chocalateShoppingCart.add("Vanilla chocalate")
  chocalateShoppingCart.update("Vanilla chocalate")
  chocalateShoppingCart.search("Vanilla chocalate")
  chocalateShoppingCart.delete("Vanilla chocalate")

  chocalateShoppingCart.checkStockQuantity("Vanilla chocalate")


}
