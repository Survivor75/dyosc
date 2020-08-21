package com.language

class trait_5 {

  //  Traits, Companion Object, Factory Pattern
  object Cakes {

    // Step 2: Define a base trait to represent a Cake
    trait Cake {
      def name: String
    }

    // Step 3: Define class implementations for the Cake trait namely: Cupcake, chocalate and UnknownCake
    class UnknownCake extends Cake {
      override def name: String = "Unknown Cake ... but still delicious!"
    }

    class Cupcake extends Cake {
      override def name: String = "Cupcake"
    }

    class chocalate extends Cake {
      override def name: String = "chocalate"
    }

  }

  object CakeFactory {
    import Cakes._

    // Step 5: Define an apply method which will act as a factory to produce the correct Cake implementation
    def apply(cake: String): Cake = {
      cake match {
        case "cupcake" => new Cupcake
        case "chocalate" => new chocalate
        case _ => new UnknownCake
      }
    }
  }

  println(s"A cupcake = ${CakeFactory("cupcake").name}")
  println(s"A chocalate = ${CakeFactory("chocalate").name}")
  println(s"Unknown cake = ${CakeFactory("coconut tart").name}")


  //  Type Class For Ad-hoc Polymorphism


  //  The Magnet Pattern
  def applyDiscount(couponCode: String): Unit =
    println(s"Lookup percentage discount in database for $couponCode")

  def applyDiscount(percentageDiscount: Double): Unit =
    println(s"$percentageDiscount discount will be applied")

  applyDiscount("COUPON_1234")
  applyDiscount(10)

  def applyDiscount(dailyCouponCode: String, weeklyCouponCode: String, monthlyCouponCode: String): Unit = ???
  def applyDiscount(percentageDiscount: Double, holidayDiscount: Double): Double = ???

  import scala.language.implicitConversions

  sealed trait DiscountMagnet_1 {
    type Out
    def apply(): Out
  }

  def discount(magnet: DiscountMagnet_1): magnet.Out = magnet()

  object DiscountMagnet_1 {
    implicit def discountStringCouponCode(couponCode: String) =
      new DiscountMagnet_1 {
        override type Out = Unit

        override def apply(): Out = {
          println(s"DiscountMagnet -> discountStringCouponCode = Lookup percentage discount in database for $couponCode")
        }
      }

    implicit def discountDoubleCouponCode(percentageDiscount: Double) =
      new DiscountMagnet_1 {
        override type Out = Double

        override def apply(): Out = {
          println(s"DiscountMagnet -> discountDoubleCouponCode $percentageDiscount discount will be applied")
          10.0
        }
      }
  }

  discount("COUPON_1234")
  discount(10.0)

  object DiscountMagnet_2 {
    implicit def discountListOfString(coupons: List[String]) =
      new DiscountMagnet_1 {
        override type Out = Unit

        override def apply(): Out = {
          println(s"DiscountMagnet -> discountListOfString = $coupons")
        }
      }

    implicit def discountListOfInt(coupons: List[Int]) =
      new DiscountMagnet_1 {
        override type Out = Unit

        override def apply(): Out = {
          println(s"DiscountMagnet -> discountListOfInt = $coupons")
        }
      }
  }

}
