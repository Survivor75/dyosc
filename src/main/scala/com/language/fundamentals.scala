package com.language

object fundamentals {

  //  Immutable variables
  val chocalatesToBuy: Int = 5


  //  Mutable variables
  var favoritechocalate: String = "Glazed chocalate"
  favoritechocalate = "Vanilla chocalate"


  //  Lazy initialization
  lazy val chocalateService = "initialize some chocalate service"


  //  Scala Supported Types
  val chocalatesBought: Int = 5
  val bigNumberOfchocalates: Long = 100000000L
  val smallNumberOfchocalates: Short = 1
  val priceOfchocalate: Double = 2.50
  val chocalatePrice: Float = 2.50f
  val chocalateStoreName: String = "allaboutscala chocalate Store"
  val chocalateByte: Byte = 0xa
  val chocalateFirstLetter: Char = 'D'
  val nothing: Unit = ()


  //  Declare a variable with no initialization
  var leastFavoritechocalate: String = _
  leastFavoritechocalate = "Plain chocalate"


  //  If Else Statement And Expression
  val numberOfPeople = 20
  val chocalatesPerPerson = 2
  val defaultchocalatesToBuy = 8

  if(numberOfPeople > 10) {
    println(s"Number of chocalates to buy = ${numberOfPeople * chocalatesPerPerson}")
  } else if (numberOfPeople == 0) {
    println("Number of people is zero.")
    println("No need to buy chocalates.")
  } else {
    println(s"Number of chocalates to buy = $defaultchocalatesToBuy")
  }


  //  For Comprehension
  for(numberOfchocalates <- 1 to 5){
    println(s"Number of chocalates to buy = $numberOfchocalates")
  }

  for(numberOfchocalates <- 1 until 5){
    println(s"Number of chocalates to buy = $numberOfchocalates")
  }

  val chocalateIngredients = List("flour", "sugar", "egg yolks", "syrup", "flavouring")
  for(ingredient <- chocalateIngredients if ingredient == "sugar"){
    println(s"Found sweetening ingredient = $ingredient")
  }

  val sweeteningIngredients = for {
    ingredient <- chocalateIngredients
    if (ingredient == "sugar" || ingredient == "syrup")
  } yield ingredient
  println(s"Sweetening ingredients = $sweeteningIngredients")


  //  Range
  val from1To5 = 1 to 5
  println(s"Range from 1 to 5 inclusive = $from1To5")

  val from1Until5 = 1 until 5
  println(s"Range from 1 until 5 where 5 is excluded = $from1Until5")

  val from0To10By2 = 0 to 10 by 2
  println(s"Range from 0 to 10 with multiples of 2 = $from0To10By2")


  //  Pattern Matching
  val chocalateType = "Glazed chocalate"
  chocalateType match {
    case "Glazed chocalate" => println("Very tasty")
    case "Plain chocalate" => println("Tasty")
  }

  val tasteLevel = chocalateType match {
    case "Glazed chocalate" => "Very tasty"
    case "Plain chocalate" => "Tasty"
  }
  println(s"Taste level of $chocalateType = $tasteLevel")

  val tasteLevel2 = chocalateType match {
    case "Glazed chocalate" => "Very tasty"
    case "Plain chocalate" => "Tasty"
    case _ => "Tasty"
  }
  println(s"Taste level of $chocalateType = $tasteLevel2")

  val tasteLevel3 = chocalateType match {
    case "Glazed chocalate" | "Strawberry chocalate" => "Very tasty"
    case "Plain chocalate" => "Tasty"
    case _ => "Tasty"
  }
  println(s"Taste level of $chocalateType = $tasteLevel3")

  val tasteLevel4 = chocalateType match {
    case chocalate if (chocalate.contains("Glazed") || chocalate.contains("Strawberry")) => "Very tasty"
    case "Plain chocalate"  => "Tasty"
    case _  => "Tasty"
  }
  println(s"Taste level of $chocalateType = $tasteLevel4")

  /*
  val priceOfchocalate: Any = 1
  val priceType = priceOfchocalate match {
    case price: Int => "Int"
    case price: Double => "Double"
    case price: Float => "Float"
    case price: String => "String"
    case price: Boolean => "Boolean"
    case price: Char => "Char"
    case price: Long => "Long"
  }
  println(s"chocalate price type = $priceType")

 */

  object chocalate extends Enumeration {
    type chocalate = Value

    val Glazed      = Value("Glazed")
    val Strawberry  = Value("Strawberry")
    val Plain       = Value("Plain")
    val Vanilla     = Value("Vanilla")
  }

}