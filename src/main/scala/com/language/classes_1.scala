package com.language

object classes_1 {

  //  Create Classes And Objects
  class chocalate(name: String, productCode: Long) {

    def print = println(s"chocalate name = $name, productCode = $productCode")

  }

  val glazedchocalate = new chocalate("Glazed chocalate", 1111)
  val vanillachocalate = new chocalate("Vanilla chocalate", 2222)


  //  Use Companion Objects
  object chocalate {

    def apply(name: String, productCode: Long): chocalate = {
      new chocalate(name, productCode)
    }

  }

}
