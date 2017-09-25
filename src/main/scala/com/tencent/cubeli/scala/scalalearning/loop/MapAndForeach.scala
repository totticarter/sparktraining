package com.tencent.cubeli.scala.scalalearning.loop

/**
  * Created by cubeli on 2017/9/25.
  */
object MapAndForeach {

  def main(args: Array[String]): Unit = {

    val list = List(1,2,3,4,5)

    //foreach没有返回值
    list.foreach(println)

    //map有返回值
    list.map(a => a - 1).foreach(println)

    list.map(_ - 1).foreach(println)

    def sub(a:Int):Int = {
      a-1
    }

  }

}
