package com.tencent.cubeli.scala.collection

/**
  * Created by waixingren on 9/5/17.
  */
object ListT {


  def main(args: Array[String]): Unit = {

    val list = List(1,2,3,4,5)
    val listStr = List("one", "two", "three")
    listStr.map(str => str+"subfix").foreach(println)
    listStr.map(_+"subfix").foreach(println(_))
  }
}
