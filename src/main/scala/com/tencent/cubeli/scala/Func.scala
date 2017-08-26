package com.tencent.cubeli.scala

/**
  * Created by waixingren on 8/25/17.
  */
object Func {

  def main(args: Array[String]): Unit = {

    val confFileName:String = "spark.properties"
    initConf(confFileName)
    println(initConf2(confFileName))
    println(initConf3(confFileName))
  }

  def initConf(confFileName: String) = println("init conf success!!!")
  def initConf2(confFileName: String): String = "init " + confFileName + " successfully in initConf2!!"
  def initConf3(confFileName: String): String = {

    val returnStr = "init " + confFileName + " successfully in initConf3"
    returnStr
  }

}
