package com.dyosc

import java.util.concurrent.Executors

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object futures_3 {

  //  Future configure threadpool
  val executor = Executors.newSingleThreadExecutor()
  implicit val ec = scala.concurrent.ExecutionContext.fromExecutor(executor)

  def donutStock(donut: String): Future[Int] = Future {

    println("checking donut stock")
    10
  }

  val donutStockOperation = donutStock("vanilla donut")
  donutStockOperation.onComplete {
    case Success(donutStock)  => println(s"Results $donutStock")
    case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(3000)
  executor.shutdownNow()


  //  Future recover
  donutStock("unknown donut")
    .recover { case e: IllegalStateException if e.getMessage == "Out of stock" => 0 }
    .onComplete {
      case Success(donutStock)  => println(s"Results $donutStock")
      case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
    }


  //  Future promise
  val donutStockPromise = Promise[Int]()

  val donutStockFuture = donutStockPromise.future
  donutStockFuture.onComplete {
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
  }

  val donut = "vanilla donut"
  if(donut == "vanilla donut") {
    donutStockPromise.success(donutStock(donut))
  } else {
    donutStockPromise.failure(Try(donutStock(donut)).failed.get)
  }


}
