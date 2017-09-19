package com.tencent.cubeli.scala.scalalearning.function

/**
  * Created by waixingren on 8/29/17.
  * 1.把匿名函数赋值给一个变量
  * 2.不把匿名函数进行赋值，直接使用:指明参数类型和自动推导参数类型
  */
object SpecialUse {

  def main(args: Array[String]): Unit = {


    var sumv = (x:Int, y:Int) => {

      x+y
//      return x+10
    }

    println(sumv(1,10))


    val list = List(1,2,3,4,5)
    list.map(x => x+1).foreach(println)

    //带参数类型
    list.filter((x: Int) => x>3).foreach(println)

    //不带参数类型
    list.filter(x => x>3).foreach(println)

    println("==================")

    //占位符
    list.filter(_ > 3).foreach(println)


    //部分应用函数
    list.filter(_ > 3).foreach(println _)

  }





}
