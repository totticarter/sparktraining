package com.tencent.cubeli.scala.apply

/**
  * Created by waixingren on 8/30/17.
  */
object MethodApply {

  def main(args: Array[String]): Unit = {


    val sumfuncv = (x: Int, y: Int) => {

      x+y
    }

    println(sumfuncv.apply(1,2))
    println(sumfuncv(1,2))

    //scala集合中apply的使用
    val list = List.apply(1,2,3,4)

  }



}
