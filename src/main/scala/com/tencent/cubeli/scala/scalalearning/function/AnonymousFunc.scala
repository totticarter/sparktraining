package com.tencent.cubeli.scala.scalalearning.function

object AnonymousFunc {

  def main(args: Array[String]): Unit = {

    //参数需要有参数类型和括号
    val funVar = (x:Int) =>{

      x+1
    }
    println(funVar(2))

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

}
