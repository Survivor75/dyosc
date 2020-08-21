package com.language

import java.util.concurrent.Executors

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object futures_3 {

  //  Future configure threadpool
  val executor = Executors.newSingleThreadExecutor()
  implicit val ec = scala.concurrent.ExecutionContext.fromExecutor(executor)

  def chocalateStock(chocalate: String): Future[Int] = Future {

    println("checking chocalate stock")
    10
  }

  val chocalateStockOperation = chocalateStock("vanilla chocalate")
  chocalateStockOperation.onComplete {
    case Success(chocalateStock)  => println(s"Results $chocalateStock")
    case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(3000)
  executor.shutdownNow()


  //  Future recover
  chocalateStock("unknown chocalate")
    .recover { case e: IllegalStateException if e.getMessage == "Out of stock" => 0 }
    .onComplete {
      case Success(chocalateStock)  => println(s"Results $chocalateStock")
      case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
    }


  //  Future promise
  val chocalateStockPromise = Promise[Int]()

  val chocalateStockFuture = chocalateStockPromise.future
  chocalateStockFuture.onComplete {
    case Success(stock) => println(s"Stock for vanilla chocalate = $stock")
    case Failure(e)     => println(s"Failed to find vanilla chocalate stock, exception = $e")
  }

  val chocalate = "vanilla chocalate"
  if(chocalate == "vanilla chocalate") {
    chocalateStockPromise.success(chocalateStock(chocalate))
  } else {
    chocalateStockPromise.failure(Try(chocalateStock(chocalate)).failed.get)
  }


}
