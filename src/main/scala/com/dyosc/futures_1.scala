package com.dyosc

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object futures_1 {

  //  Define a method which returns a Future
  def donutStock(donut: String): Future[Option[Int]] = Future {
    println("checking donut stock")
    if(donut == "vanilla donut") Some(10) else None
  }

  val vanillaDonutStock = Await.result(donutStock("vanilla donut"), 5 seconds)
  println(s"Stock of vanilla donut = $vanillaDonutStock")


  //  Non blocking future result
  donutStock("vanilla donut").onComplete {
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(e) => println(s"Failed to find vanilla donut stock, exception = $e")
  }
  Thread.sleep(3000)


  //  Chain futures using flatMap
  def buyDonuts(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts")
    true
  }

  val buyingDonuts: Future[Boolean] = donutStock("plain donut").flatMap(qty => buyDonuts(qty.getOrElse(0)))

  val isSuccess = Await.result(buyingDonuts, 5 seconds)
  println(s"Buying vanilla donut was successful = $isSuccess")


  //  Chain futures using for comprehension
  for {
    stock     <- donutStock("vanilla donut")
    isSuccess <- buyDonuts(stock.getOrElse(0))
  } yield println(s"Buying vanilla donut was successful = $isSuccess")

  Thread.sleep(3000)


  //  Future option with for comprehension
  for {
    someStock  <- donutStock("vanilla donut")
    isSuccess  <- buyDonuts(someStock.getOrElse(0))
  } yield println(s"Buying vanilla donut was successful = $isSuccess")


  //  Future option with map
  donutStock("vanilla donut")
    .map(someQty => println(s"Buying ${someQty.getOrElse(0)} vanilla donuts"))


  //  Composing futures
  val resultFromMap: Future[Future[Boolean]] = donutStock("vanilla donut")
    .map(someQty => buyDonuts(someQty.getOrElse(0)))
  Thread.sleep(1000)

  val resultFromFlatMap: Future[Boolean] = donutStock("vanilla donut")
    .flatMap(someQty => buyDonuts(someQty.getOrElse(0)))
  Thread.sleep(1000)


  //  Future sequence
  def processPayment(): Future[Unit] = Future {
    println("processPayment ... sleep for 1 second")
    Thread.sleep(1000)
  }
  val futureOperations: List[Future[Any]] = List(donutStock("vanilla donut"), buyDonuts(10), processPayment())

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
