package com.tencent.cubeli.scala.scalalearning.loop
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
object For {

  def main(args: Array[String]): Unit = {

    //yield的使用:如果没有yield，那么如果要把文件里的每行放进一个数组里，需要先定义一个数组
    //但是如果有yield，则直接定义一个val，使用for yield把文件中的每行通过yield返回到val即可
    val file = Source.fromFile("D:\\bigdata\\training\\scala\\ip.txt")
    var lineArray = new ArrayBuffer[String]()

    //对元素简单的处理可以不加大括号
//    val lines = for(line <- file.getLines()) yield "ip:" + line
//    lines.foreach(println)

    //对元素复杂的处理，可以加上大括号
    val lines2 = for(line <- file.getLines()) yield {

      var l = ""
      if(line.startsWith("192")){
        l = "ip start with 192 are: " + line
      }
      //如果这里不显示的增加一个l作为返回值，不会产生预期效果
      l
    }
    lines2.foreach(println)

    mapTest()
//    val list = List(1,2,3,4)
//    for(l <- list) yield l.toString+"aaa"
  }

  def mapTest(): Unit ={

    val map = Map(1->"one", 2->"two", 3->"three")
    val k = for(m <- map) yield{
      m._1.toString + "hehehe"
    }

    k.foreach(println)
  }
}
