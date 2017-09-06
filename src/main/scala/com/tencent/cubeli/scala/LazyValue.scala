package com.tencent.cubeli.scala

import scala.io.Source._
/**
  * Created by waixingren on 8/25/17.
  */
object LazyValue {

  def main(args: Array[String]): Unit = {

    lazy val lines = fromFile("/Users/waixingren/bigdata-java/spark/sparkproj/" +
      "src/main/scala/com/tencent/cubeli/scala/Func.scala").mkString
    lines.foreach(print)

  }

}
