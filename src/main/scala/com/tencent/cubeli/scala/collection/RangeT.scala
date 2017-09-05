package com.tencent.cubeli.scala.collection

/**
  * Created by waixingren on 9/5/17.
  */
object RangeT {

  def main(args: Array[String]): Unit = {

    val a = 1 to 10
    val b = 1 until 8
    val c = 1 to 10 by 2
    println(a.getClass.getName)
    c.foreach(println)
  }

}
