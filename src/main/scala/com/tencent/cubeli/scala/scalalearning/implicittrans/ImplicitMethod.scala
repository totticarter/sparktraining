package com.tencent.cubeli.scala.scalalearning.implicittrans

/**
  * Scala中的隐式转换是一种非常强大的代码查找机制。
  * 当函数、构造器调用缺少参数或者某一实例调用了其他类型的方法导致编译不通过时，编译器会尝试搜索一些特定的区域，尝试使编译通过。
  * Created by waixingren on 8/30/17.
  * 演示implicit function：
  * 如果从一个方法返回的是一个string表示的int，需要和另外一个int类型比较大小，如果为了代码美观，可以不用自己把string转换成int，定义一个隐式转换方法即可
  */

/**
  * 隐式转换函数是指在同一个作用域下面，一个给定输入类型并自动转换为指定返回类型的函数，
  * 这个函数和函数名字无关，和入参名字无关，只和入参类型以及返回类型有关
  *
  * @param str
  * @return
  */
object ImplicitMethod {


  object Impl{

    implicit  def strToInt(str:String):Int = {

      //编译不报错，执行报错
//      str.toInt

      Integer.parseInt(str)

    }
  }
  def main(args: Array[String]): Unit = {

    val m = math.max(1,2)
    import Impl.strToInt //演示如果没有这个隐式转换方法，下边的max编译会报错
    val n2 = math.max("1", 2)
    println(n2)
  }
}
