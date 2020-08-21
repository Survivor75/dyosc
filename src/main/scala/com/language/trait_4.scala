package com.language

class trait_4 {

  //  Avoid Cake Pattern
  class chocalateInventoryService[T] {
    def checkStock(chocalate: T): Boolean = {
      println("chocalateInventoryService->checkStock")
      true
    }
  }

  class chocalatePricingService[T] {
    def calculatePrice(chocalate: T): Double = {
      println("chocalatePricingService->calculatePrice")
      2.50
    }
  }

  class chocalateOrderService[T] {
    def createOrder(chocalate: T, quantity: Int, price: Double): Int = {
      println(s"Saving chocalate order to database: chocalate = $chocalate, quantity = $quantity, price = $price")
      100 // the id of the booked order
    }
  }

  class chocalateShoppingCartService[T] (
                                      chocalateInventoryService: chocalateInventoryService[T],
                                      chocalatePricingService: chocalatePricingService[T],
                                      chocalateOrderService: chocalateOrderService[T]) {

    def bookOrder(chocalate: T, quantity: Int): Int = {
      println("chocalateShoppingCartService->bookOrder")

      chocalateInventoryService.checkStock(chocalate) match {
        case true =>
          val price = chocalatePricingService.calculatePrice(chocalate)
          chocalateOrderService.createOrder(chocalate, quantity, price) // the id of the booked order

        case false =>
          println(s"Sorry chocalate $chocalate is out of stock!")
          -100 // return some error code to identify out of stock
      }
    }
  }

  trait chocalateStoreServices {
    val chocalateInventoryService = new chocalateInventoryService[String]
    val chocalatePricingService = new chocalatePricingService[String]
    val chocalateOrderService = new chocalateOrderService[String]
    val chocalateShoppingCartService = new chocalateShoppingCartService(chocalateInventoryService, chocalatePricingService, chocalateOrderService)
  }

  trait chocalateStoreAppController {
    this: chocalateStoreServices =>

    def bookOrder(chocalate: String, quantity: Int): Int = {
      println("chocalateStoreAppController->bookOrder")
      chocalateShoppingCartService.bookOrder(chocalate, quantity)
    }
  }

  object chocalateStoreApp extends chocalateStoreAppController with chocalateStoreServices

  trait MockedchocalateStoreServices extends chocalateStoreServices {
    override val chocalateInventoryService: chocalateInventoryService[String] = ???
    override val chocalatePricingService: chocalatePricingService[String] = ???
    override val chocalateOrderService: chocalateOrderService[String] = ???
    override val chocalateShoppingCartService: chocalateShoppingCartService[String] = new chocalateShoppingCartService[String](
      chocalateInventoryService, chocalatePricingService, chocalateOrderService)
  }

  object MockedchocalateStoreApp extends chocalateStoreAppController with MockedchocalateStoreServices

}
