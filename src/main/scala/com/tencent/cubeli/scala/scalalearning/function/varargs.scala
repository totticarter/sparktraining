package com.tencent.cubeli.scala.scalalearning.function

/**
  * Created by waixingren on 03/11/2017.
  */
object varargs {


  def main(args: Array[String]): Unit = {


    echo("a", "b", "c", "d")

  }

  def echo(args: String*): Unit = {

    for(arg:String <- args){

      println(arg)
    }
  }
}
