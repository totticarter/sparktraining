package com.tencent.cubeli.scala.scalaplusjava
import java.util
import java.util.HashMap

/**
  * Created by waixingren on 8/30/17.
  */
object UseJava {

  def main(args: Array[String]): Unit = {

    var map = new util.HashMap[String, Int]()
    map.put("one", 1)
    map.put("two", 2)

    val value = map.get("one")

//    map.forEach(println)
  }
}
