package com.tencent.cubeli.scala.scalalearning.collection

/**
  * Created by waixingren on 9/5/17.
  */
object SetT {

  def main(args: Array[String]): Unit = {

    var set = scala.collection.immutable.Set(1,2,3,3)
    //set.foreach(println)

    set += 5
    set.foreach(println)


    println(set.exists(_ % 3 == 0))
    println(set.contains(3))
  }

}
