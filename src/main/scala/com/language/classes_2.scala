package com.language

object classes_2 {

  //  Use Companion Objects' Apply Method As A Factory (Class Hierarchy Via Inheritance)
  class chocalate(name: String, productCode: Option[Long] = None){

    def print = println(s"chocalate name = $name, productCode = ${productCode.getOrElse(0)}")

  }

  class Glazedchocalate(name: String) extends chocalate(name)
  class Vanillachocalate(name: String) extends chocalate(name)

  object chocalate {

    def apply(name: String): chocalate = {
      name match {
        case "Glazed chocalate" => new Glazedchocalate(name)
        case "Vanilla chocalate" => new Vanillachocalate(name)
        case _ => new chocalate(name)
      }
    }

  }

  val glazedchocalate = chocalate("Glazed chocalate")
  println(s"The class type of glazedchocalate = ${glazedchocalate.getClass}")
  glazedchocalate.print

  val vanillachocalate = chocalate("Vanilla chocalate")
  println(s"The class type of vanillachocalate = ${vanillachocalate.getClass}")
  vanillachocalate.print

}
