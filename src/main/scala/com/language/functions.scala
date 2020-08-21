package com.language

import scala.util.Random

object functions {

  //  How to define and use a function which has no parameters and has a return type
  def favoritechocalate(): String = {
    "Glazed chocalate"
  }

  val myFavoritechocalate = favoritechocalate()
  println(s"My favorite chocalate is $myFavoritechocalate")


  //  How to define and use a function with no return type
  def printchocalateSalesReport(): Unit = {

    println("Printing chocalate sales report... done!")
  }
  printchocalateSalesReport()


  //  How to define function with parameters
  def calculatechocalateCost(chocalateName: String, quantity: Int=1, couponCode: Option[String]=None): Option[Double] = {
    println(s"Calculating cost for $chocalateName, quantity = $quantity")

    Some(2.50 * quantity)
  }


  //  How to define a function which takes implicit parameter(s)
  def totalCost(chocalateType: String, quantity: Int)(implicit discount: Double, storeName: String): Double = {
    println(s"Calculating the price for $quantity $chocalateType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  implicit val discount: Double = 0.1
  implicit val storeName: String = "Tasty chocalate Store"

  println(s"""Total cost with discount of 5 Glazed chocalates = ${totalCost("Glazed chocalate", 5)}""")


  //  How to create a wrapper String class which will extend the String type

  class chocalateString(s: String) {

    def isFavoritechocalate: Boolean = s == "Glazed chocalate"

  }


  //  How to create an implicit function to convert a String to the wrapper String class
  object chocalateConverstions {
    implicit def stringTochocalateString(s: String) = new chocalateString(s)
  }


  //  How to import the String conversion so that it is in scope
  import chocalateConverstions._

  val glazedchocalate = "Glazed chocalate"
  val vanillachocalate = "Vanilla chocalate"


  //  How to access the custom String function called isFavaoritechocalate
  println("\nStep 5: How to access the custom String function called isFavaoritechocalate")
  println(s"Is Glazed chocalate my favorite chocalate = ${glazedchocalate.isFavoritechocalate}")
  println(s"Is Vanilla chocalate my favorite chocalate = ${vanillachocalate.isFavoritechocalate}")


  //  Typed Function
  def applyDiscount(couponCode: String) {
    println(s"Lookup percentage discount in database for $couponCode")
  }

  def applyDiscount(percentageDiscount: Double) {
    println(s"$percentageDiscount discount will be applied")
  }

  applyDiscount("COUPON_1234")
  applyDiscount(10)

  def applyDiscount[T](discount: T) {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")

      case d: Double =>
        println(s"$d discount will be applied")

      case _ =>
        println("Unsupported discount type")
    }
  }

  applyDiscount[String]("COUPON_123")
  applyDiscount[Double](10)


  //  Polymorphic Function With Generic Return Type
  def applyDiscountWithReturnType[T](discount: T): Seq[T] = {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")
        Seq[T](discount)

      case d: Double =>
        println(s"$d discount will be applied")
        Seq[T](discount)

      case d @ _ =>
        println("Unsupported discount type")
        Seq[T](discount)
    }
  }


  //  Variable Argument Function
  def printReport(names: String*) {
    println(s"""chocalate Report = ${names.mkString(" - ")}""")
  }

  printReport("Glazed chocalate", "Strawberry chocalate", "Vanilla chocalate")
  printReport("Chocolate chocalate")

  val listchocalates: List[String] = List("Glazed chocalate", "Strawberry chocalate", "Vanilla chocalate")

  printReport(listchocalates: _*)


  //  Functions As Symbols
  class chocalateCostCalculator {

    // We are hard-coding the totalCost value for simplicity.
    val totalCost = 100

    def minusDiscount(discount: Double): Double = {
      totalCost - discount
    }

    def -(discount: Double): Double = {
      totalCost - discount
    }

    def +++(taxAmount: Double): Double = {
      totalCost + taxAmount
    }
  }

  val chocalateCostCalculator = new chocalateCostCalculator()

  println(s"Calling minusDiscount() function = ${chocalateCostCalculator.minusDiscount(10.5)}")

  println(s"Calling function - = ${chocalateCostCalculator.-(10.5)}")

  println(s"Calling function - with operator style notation = ${chocalateCostCalculator - 10.5}")


  //  Function Currying With Parameter Groups
  def totalCost(chocalateType: String)(quantity: Int)(discount: Double): Double = {
    println(s"Calculating total cost for $quantity $chocalateType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }

  println(s"Total cost = ${totalCost("Glazed chocalate")(10)(0.1)}")

  val totalCostForGlazedchocalates = totalCost("Glazed chocalate") _

  println(s"\nTotal cost for Glazed chocalates ${totalCostForGlazedchocalates(10)(0.1)}")


  //  Higher Order Function - Function As Parameter
  def totalCostWithDiscountFunctionParameter(chocalateType: String)(quantity: Int)(f: Double => Double): Double = {
    println(s"Calculating total cost for $quantity $chocalateType")
    val totalCost = 2.50 * quantity
    f(totalCost)
  }

  val totalCostOf5chocalates = totalCostWithDiscountFunctionParameter("Glazed chocalate")(5){totalCost =>
    val discount = 2
    totalCost - discount
  }
  println(s"Total cost of 5 Glazed chocalates with anonymous discount function = $totalCostOf5chocalates")
  def applyDiscount(totalCost: Double): Double = {
    val discount = 2
    totalCost - discount

  }


  //  println(s"Total cost of 5 Glazed chocalates with discount function = ${totalCostWithDiscountFunctionParameter("Glazed chocalate")(5)(applyDiscount(_))}")


  //  Higher Order Function - Call-By-Name Function
  def placeOrder(orders: List[(String, Int, Double)])(exchangeRate: Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }

  def placeOrderWithByNameParameter(orders: List[(String, Int, Double)])(exchangeRate: => Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }

  val randomExchangeRate = new Random(10)
  def usdToGbp: Double = {
    val rate = randomExchangeRate.nextDouble()
    println(s"Fetching USD to GBP exchange rate = $rate")
    rate
  }


  //  Function Using The Val Keyword Instead Of Def
  val applyDiscountValueFunction = (totalCost: Double) => {
    val discount = 2 // assume you fetch discount from database
    totalCost - discount
  }


  //  Function Composition Using AndThen
  val applyDiscountValFunction = (amount: Double) => {
    println("Apply discount function")
    val discount = 2 // fetch discount from database
    amount - discount
  }

  val totalCost: Double = 10

  println(s"Total cost of 5 chocalates with discount = ${applyDiscountValFunction(totalCost)}")

  val applyTaxValFunction = (amount: Double) => {
    println("Apply tax function")
    val tax = 1 // fetch tax from database
    amount + tax
  }

  println(s"Total cost of 5 chocalates = ${ (applyDiscountValFunction andThen applyTaxValFunction)(totalCost) }")


  //  Function Composition Using Compose
  println(s"Total cost of 5 chocalates = ${ (applyDiscountValFunction compose applyTaxValFunction)(totalCost) }")


  //  Tail Recursive Function - @annotation.tailrec
  val arraychocalates: Array[String] = Array("Vanilla chocalate", "Strawberry chocalate", "Plain chocalate", "Glazed chocalate")

  @annotation.tailrec
  def search(chocalateName: String, chocalates: Array[String], index: Int): Option[Boolean] = {
    if(chocalates.length == index) {
      None
    } else if(chocalates(index) == chocalateName) {
      Some(true)
    } else {
      val nextIndex = index + 1
      search(chocalateName, chocalates, nextIndex)
    }
  }

  val found = search("Glazed chocalate", arraychocalates, 0)
  println(s"Find Glazed chocalate = $found")

  val notFound = search("Chocolate chocalate", arraychocalates, 0)
  println(s"Find Chocolate chocalate = $notFound")


}
