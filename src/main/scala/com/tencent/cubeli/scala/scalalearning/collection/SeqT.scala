package com.tencent.cubeli.scala.scalalearning.collection

/**
  * Created by cubeli on 2017/9/25.
  */
object SeqT {

  def main(args: Array[String]): Unit = {

    val seq = Seq(1,2,3)
    //seq.foreach(println)

    val (a,b) = seq.partition(_ %2==0)
    a.foreach(println)
    b.foreach(println)

    seq.map(_.toString).exists(_.equals("1"))
  }

}
