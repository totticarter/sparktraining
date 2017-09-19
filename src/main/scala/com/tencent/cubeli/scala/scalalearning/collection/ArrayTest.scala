package com.tencent.cubeli.scala.scalalearning.collection

import scala.collection.mutable.ArrayBuffer

/**
  * Created by waixingren on 8/25/17.
  */
object ArrayTest {

  def main(args: Array[String]): Unit = {


    //val arr = Array("one", "two")
    //arr.foreach(println)

    var arrbuf = new ArrayBuffer[String]()
    arrbuf += "one"
    arrbuf += "two"
    arrbuf += "three"
    arrbuf.foreach(println)

    val idxs = 0 until 3
    for(idx <- idxs){
      println(arrbuf(idx))
    }

  }
}
