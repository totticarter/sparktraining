package com.tencent.cubeli.scala.function
import scala.io.Source

/**
  * Created by waixingren on 8/29/17.
  * 1.函数返回值的定义，不定义默认生成一个Unit
  * 2.是否需要显式的写return
  * 3.在一个方法内部定义私有的方法
  */
object CommonUse {

  def main(args: Array[String]): Unit = {


    processIpFile("/Users/waixingren/bigdata-java/spark/sparkproj/data/ip.txt", "192")
  }

  def processIpFile(fileName:String, prefix: String): Unit ={


    def processLine3(line: String, prefix: String):Boolean = {


      if(line.startsWith(prefix)){

        return true
      }else{
        return false
      }
    }


    val file = Source.fromFile(fileName)

    for(line <- file.getLines()){
      if(processLine3(line, prefix)){
        println(line)
      }
    }
  }

  def processLine1(line: String, prefix: String):Boolean = {


    if(line.startsWith(prefix)){

      return true
    }else{
      return false
    }
  }

  def processLine2(line:String, prefix: String):Boolean = {

    var flag:Boolean = false
    if(line.startsWith(prefix)){
      flag = true
    }else{
      flag = false
    }

    flag
  }
}
