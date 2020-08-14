package com.dyosc

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class futures_2 {

  //  Future foldLeft
  def donutStock(donut: String): Future[Option[Int]] = Future {
    println("checking donut stock")
    if(donut == "vanilla donut") Some(10) else None
  }

  val futureOperations = List(
    donutStock("vanilla donut"),
    donutStock("plain donut"),
    donutStock("chocolate donut"),
    donutStock("vanilla donut")
  )

  val futureFoldLeft = Future.foldLeft(futureOperations)(0){ case (acc, someQty) =>
    acc + someQty.getOrElse(0)
  }
  futureFoldLeft.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future reduceLeft
  val futureReduceLeft = Future.reduceLeft(futureOperations){ case (acc, someQty) =>
    acc.map(qty => qty + someQty.getOrElse(0))
  }
  futureFoldLeft.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future firstCompletedOf
  val futureFirstCompletedResult = Future.firstCompletedOf(futureOperations)
  futureFirstCompletedResult.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future zip
  def donutPrice(): Future[Double] = Future.successful(3.25)

  val donutStockAndPriceOperation = donutStock("vanilla donut") zip donutPrice()

  donutStockAndPriceOperation.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future zipWith
  val qtyAndPriceF: (Option[Int], Double) => (Int, Double) = (someQty, price) => (someQty.getOrElse(0), price)

  val donutAndPriceOperation = donutStock("vanilla donut").zipWith(donutPrice())(qtyAndPriceF)
  donutAndPriceOperation.onComplete {
    case Success(result) => println(s"Result $result")
    case Failure(e)      => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future andThen
  val donutStockOperation = donutStock("vanilla donut")
  donutStockOperation.andThen { case stockQty => println(s"Donut stock qty = $stockQty")}

}
