package com.language

object classes_2 {

  //  Use Companion Objects' Apply Method As A Factory (Class Hierarchy Via Inheritance)
  class Donut(name: String, productCode: Option[Long] = None){

    def print = println(s"Donut name = $name, productCode = ${productCode.getOrElse(0)}")

  }

  class GlazedDonut(name: String) extends Donut(name)
  class VanillaDonut(name: String) extends Donut(name)

  object Donut {

    def apply(name: String): Donut = {
      name match {
        case "Glazed Donut" => new GlazedDonut(name)
        case "Vanilla Donut" => new VanillaDonut(name)
        case _ => new Donut(name)
      }
    }

  }

  val glazedDonut = Donut("Glazed Donut")
  println(s"The class type of glazedDonut = ${glazedDonut.getClass}")
  glazedDonut.print

  val vanillaDonut = Donut("Vanilla Donut")
  println(s"The class type of vanillaDonut = ${vanillaDonut.getClass}")
  vanillaDonut.print

}
