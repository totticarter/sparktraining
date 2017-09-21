package com.tencent.cubeli.scala.scalaapp
import scala.io.Source._

/**
  * Created by cubeli on 2017/9/19.
  */
object pythonfileprocess {

  def main(args: Array[String]): Unit = {

    val o = new Object()
    val b = o.asInstanceOf[String]


    val orgiFile = fromFile("E:\\bigdata\\crawler\\com\\tencent\\cubeli\\hash\\consistenthash.py")
    for(line <- orgiFile.getLines()){

      if(!line.isEmpty){

          println(line)
      }
    }

  }

}
