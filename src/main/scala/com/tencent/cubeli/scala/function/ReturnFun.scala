package com.tencent.cubeli.scala.function

object ReturnFun {

  def main(args: Array[String]): Unit = {


    var f = returnConcatFun("aaa") //这里只是类似于定义了一个函数，还没有调用这个函数
    f(1)//这里才开始调用这个函数
  }

  def returnConcatFun(value: String) = (i: Int) => {

    println(i.toString + " func")
  }

}
