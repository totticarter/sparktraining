package com.tencent.cubeli.scala.scalalearning.collection

import java.util

/**
  * Created by liyong on 15/06/2018.
  */
object ConvertToScalaCollection {

  def main(args: Array[String]) {

    val javaSet = new util.HashSet[String]()
    javaSet.add("one")
    javaSet.add("two")
    javaSet.add("three")
    import scala.collection.JavaConverters._
    val scalaSet = javaSet.asScala

    val javaList = new util.ArrayList[String]()
    javaList.add("11111")
    javaList.add("22222")
    val scalaList = javaList.asScala

    val javaMap = new util.HashMap[String, Integer]()
    javaMap.put("one", 1)
    javaMap.put("two", 2)
    val scalaMap = javaMap.asScala
    scalaMap.foreach(e => println(e._1 + ", " + e._2))

  }

}
