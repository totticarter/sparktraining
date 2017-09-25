package com.tencent.cubeli.scala.scalalearning.none

/**
  * Created by cubeli on 2017/9/25.
  */
object NilT {

  def main(args: Array[String]): Unit = {

    println (Nil == List())
    println (Nil eq List())
    println (Nil equals List())
    System.identityHashCode(Nil)
    System.identityHashCode(List())
  }

}
