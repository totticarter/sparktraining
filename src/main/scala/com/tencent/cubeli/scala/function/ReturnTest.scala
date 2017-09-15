package com.tencent.cubeli.scala.function

/**
  * Created by cubeli on 2017/9/13.
  * http://blog.csdn.net/u010256841/article/details/53467905
  */
package com.tencent.cubeli.scala.function

object ReturnTest {

  def main(args: Array[String]): Unit = {

    println(sum1(1,2,3,4))
    println(sum2(1,2,3,4))

  }

  def add(n:Int, m:Int): Int = return n + m     //式3.1
  def sum1(ns: Int*): Int = ns.foldLeft(0)(add)    //式3.2
  def sum2(ns: Int*): Int = ns.foldLeft(0)((n,m) => return n+m)

  def foo: Int = {
    val sumR: List[Int] => Int = _.foldLeft(0)((n, m) => return n + m)
    sumR(List(1,2,3)) + sumR(List(4,5,6))
  }

  def foo2: Int = {

    //这种匿名函数形式不同于书上看到的语法
    val sumR: List[Int] => Int = _.foldLeft(0)((n, m) => return n + m)

    //对上边的匿名函数实现变形一下
    val sumR2: List[Int] => Int = {

      _.foldLeft(0)((n,m) => return (n+m))
    }

    /*在repl里运行这个，会发现函数的原型和上边的表达一致:sumR3: List[Int] => Int = <function1>
    **
    **
     */
    val sumR3 = (l:List[Int]) => l.foldLeft(0)((n,m) => return n+m)

    sumR(List(1,2,3)) + sumR(List(4,5,6))
  }

}
