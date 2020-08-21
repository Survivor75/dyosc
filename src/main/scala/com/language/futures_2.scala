package com.language

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class futures_2 {

  //  Future foldLeft
  def chocalateStock(chocalate: String): Future[Option[Int]] = Future {
    println("checking chocalate stock")
    if(chocalate == "vanilla chocalate") Some(10) else None
  }

  val futureOperations = List(
    chocalateStock("vanilla chocalate"),
    chocalateStock("plain chocalate"),
    chocalateStock("chocolate chocalate"),
    chocalateStock("vanilla chocalate")
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
  def chocalatePrice(): Future[Double] = Future.successful(3.25)

  val chocalateStockAndPriceOperation = chocalateStock("vanilla chocalate") zip chocalatePrice()

  chocalateStockAndPriceOperation.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future zipWith
  val qtyAndPriceF: (Option[Int], Double) => (Int, Double) = (someQty, price) => (someQty.getOrElse(0), price)

  val chocalateAndPriceOperation = chocalateStock("vanilla chocalate").zipWith(chocalatePrice())(qtyAndPriceF)
  chocalateAndPriceOperation.onComplete {
    case Success(result) => println(s"Result $result")
    case Failure(e)      => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //  Future andThen
  val chocalateStockOperation = chocalateStock("vanilla chocalate")
  chocalateStockOperation.andThen { case stockQty => println(s"chocalate stock qty = $stockQty")}

}
