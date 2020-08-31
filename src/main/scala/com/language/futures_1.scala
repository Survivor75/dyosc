package com.language

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.language.postfixOps

object futures_1 {

  //  Define a method which returns a Future
  def chocalateStock(chocalate: String): Future[Option[Int]] = Future {
    println("checking chocalate stock")
    if(chocalate == "vanilla chocalate") Some(10) else None
  }

  val vanillachocalateStock = Await.result(chocalateStock("vanilla chocalate"), 5 seconds)
  println(s"Stock of vanilla chocalate = $vanillachocalateStock")


  //  Non blocking future result
  chocalateStock("vanilla chocalate").onComplete {
    case Success(stock) => println(s"Stock for vanilla chocalate = $stock")
    case Failure(e) => println(s"Failed to find vanilla chocalate stock, exception = $e")
  }
  Thread.sleep(3000)


  //  Chain futures using flatMap
  def buychocalates(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity chocalates")
    true
  }

  val buyingchocalates: Future[Boolean] = chocalateStock("plain chocalate").flatMap(qty => buychocalates(qty.getOrElse(0)))

  val isSuccess = Await.result(buyingchocalates, 5 seconds)
  println(s"Buying vanilla chocalate was successful = $isSuccess")


  //  Chain futures using for comprehension
  for {
    stock     <- chocalateStock("vanilla chocalate")
    isSuccess <- buychocalates(stock.getOrElse(0))
  } yield println(s"Buying vanilla chocalate was successful = $isSuccess")

  Thread.sleep(3000)


  //  Future option with for comprehension
  for {
    someStock  <- chocalateStock("vanilla chocalate")
    isSuccess  <- buychocalates(someStock.getOrElse(0))
  } yield println(s"Buying vanilla chocalate was successful = $isSuccess")


  //  Future option with map
  chocalateStock("vanilla chocalate")
    .map(someQty => println(s"Buying ${someQty.getOrElse(0)} vanilla chocalates"))


  //  Composing futures
  val resultFromMap: Future[Future[Boolean]] = chocalateStock("vanilla chocalate")
    .map(someQty => buychocalates(someQty.getOrElse(0)))
  Thread.sleep(1000)

  val resultFromFlatMap: Future[Boolean] = chocalateStock("vanilla chocalate")
    .flatMap(someQty => buychocalates(someQty.getOrElse(0)))
  Thread.sleep(1000)


  //  Future sequence
  def processPayment(): Future[Unit] = Future {
    println("processPayment ... sleep for 1 second")
    Thread.sleep(1000)
  }
  val futureOperations: List[Future[Any]] = List(chocalateStock("vanilla chocalate"), buychocalates(10), processPayment())

  val futureSequenceResults = Future.sequence(futureOperations)
  futureSequenceResults.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }


  //  Future traverse
  val futureTraverseResult = Future.traverse(futureOperations){ futureSomeQty =>
    futureSomeQty.map(someQty => someQty)
  }
  futureTraverseResult.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }


}

object Test extends App {
  def chocalateStock(chocalate: String): Future[Option[Int]] = Future {
    println("checking chocalate stock")
    if(chocalate == "vanilla chocalate") Some(10) else None
  }

  def buychocalates(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity chocalates")
    true
  }
  Await.result(
  for {
    someStock  <- chocalateStock("vanill chocalate")
    isSuccess  <- buychocalates(someStock.getOrElse(0))
  } yield {
    println(s"Buying vanilla chocalate was successful = $isSuccess")
  }
  , 1 minute)
}