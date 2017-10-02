package com.tencent.cubeli.scala.scalalearning.none

/**
  * Created by waixingren on 10/1/17.
  * Scala中有Left,Right两个类，继承于Either,主要用途是表示两个可能不同的类型（它们之间没有交集）,Left主要是表示Failure,Right表示有,跟Some类型有点类似,
  *
  * Either/Left/Right和Option/Some/None很像下面是，两者之间的比较：
  * Either 和Option一样.
  * Right 和 Some一样
  * Left  和None一样, 但是你可以包含一个用来描述问题的字符串
  *
  * 一下代码演示Either、left、right的使用
  */
object RightLeft {

  def main(args: Array[String]): Unit = {


    var s = "hello"
    throwableToLeft { s.toUpperCase } match {
      case Right(s) => println(s)
      case Left(e) => e.printStackTrace
    }


    s = null
    throwableToLeft { s.toUpperCase } match {
      case Right(s) => println(s)
      case Left(e) => e.printStackTrace
    }

    //把方法的Either对象返回，赋值给一个变量
    val a = throwableToLeft{s.toUpperCase}
    if(a.isLeft){
      println("exception...")
    }else{
      println(s)
    }


  }

  def throwableToLeft[T](block: => T): Either[java.lang.Throwable, T] =
    try {
      Right(block)
    } catch {
      case ex => Left(ex)
    }
}
