package com.dyosc

import scala.util.Random

object functions {

  //  How to define and use a function which has no parameters and has a return type
  def favoriteDonut(): String = {
    "Glazed Donut"
  }

  val myFavoriteDonut = favoriteDonut()
  println(s"My favorite donut is $myFavoriteDonut")


  //  How to define and use a function with no return type
  def printDonutSalesReport(): Unit = {

    println("Printing donut sales report... done!")
  }
  printDonutSalesReport()


  //  How to define function with parameters
  def calculateDonutCost(donutName: String, quantity: Int=1, couponCode: Option[String]=None): Option[Double] = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    Some(2.50 * quantity)
  }


  //  How to define a function which takes implicit parameter(s)
  def totalCost(donutType: String, quantity: Int)(implicit discount: Double, storeName: String): Double = {
    println(s"Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  implicit val discount: Double = 0.1
  implicit val storeName: String = "Tasty Donut Store"

  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost("Glazed Donut", 5)}""")


  //  How to create a wrapper String class which will extend the String type

  class DonutString(s: String) {

    def isFavoriteDonut: Boolean = s == "Glazed Donut"

  }


  //  How to create an implicit function to convert a String to the wrapper String class
  object DonutConverstions {
    implicit def stringToDonutString(s: String) = new DonutString(s)
  }


  //  How to import the String conversion so that it is in scope
  import DonutConverstions._

  val glazedDonut = "Glazed Donut"
  val vanillaDonut = "Vanilla Donut"


  //  How to access the custom String function called isFavaoriteDonut
  println("\nStep 5: How to access the custom String function called isFavaoriteDonut")
  println(s"Is Glazed Donut my favorite Donut = ${glazedDonut.isFavoriteDonut}")
  println(s"Is Vanilla Donut my favorite Donut = ${vanillaDonut.isFavoriteDonut}")


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
    println(s"""Donut Report = ${names.mkString(" - ")}""")
  }

  printReport("Glazed Donut", "Strawberry Donut", "Vanilla Donut")
  printReport("Chocolate Donut")

  val listDonuts: List[String] = List("Glazed Donut", "Strawberry Donut", "Vanilla Donut")

  printReport(listDonuts: _*)


  //  Functions As Symbols
  class DonutCostCalculator {

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

  val donutCostCalculator = new DonutCostCalculator()

  println(s"Calling minusDiscount() function = ${donutCostCalculator.minusDiscount(10.5)}")

  println(s"Calling function - = ${donutCostCalculator.-(10.5)}")

  println(s"Calling function - with operator style notation = ${donutCostCalculator - 10.5}")


  //  Function Currying With Parameter Groups
  def totalCost(donutType: String)(quantity: Int)(discount: Double): Double = {
    println(s"Calculating total cost for $quantity $donutType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }

  println(s"Total cost = ${totalCost("Glazed Donut")(10)(0.1)}")

  val totalCostForGlazedDonuts = totalCost("Glazed Donut") _

  println(s"\nTotal cost for Glazed Donuts ${totalCostForGlazedDonuts(10)(0.1)}")


  //  Higher Order Function - Function As Parameter
  def totalCostWithDiscountFunctionParameter(donutType: String)(quantity: Int)(f: Double => Double): Double = {
    println(s"Calculating total cost for $quantity $donutType")
    val totalCost = 2.50 * quantity
    f(totalCost)
  }

  val totalCostOf5Donuts = totalCostWithDiscountFunctionParameter("Glazed Donut")(5){totalCost =>
    val discount = 2
    totalCost - discount
  }
  println(s"Total cost of 5 Glazed Donuts with anonymous discount function = $totalCostOf5Donuts")
  def applyDiscount(totalCost: Double): Double = {
    val discount = 2
    totalCost - discount

  }


  //  println(s"Total cost of 5 Glazed Donuts with discount function = ${totalCostWithDiscountFunctionParameter("Glazed Donut")(5)(applyDiscount(_))}")


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

  println(s"Total cost of 5 donuts with discount = ${applyDiscountValFunction(totalCost)}")








}
