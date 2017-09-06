package com.tencent.cubeli.scala.function

object AnonymousFunc {

  def main(args: Array[String]): Unit = {

    //参数需要有参数类型和括号
    val funVar = (x:Int) =>{

      x+1
    }

    println(funVar(2))
  }

}
