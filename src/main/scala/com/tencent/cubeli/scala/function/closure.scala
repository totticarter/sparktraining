package com.tencent.cubeli.scala.function

/**
  * Created by waixingren on 8/29/17.
  */
object closure {

  def main(args: Array[String]): Unit = {


    val addedValue = 1000
    val list = List(1,2,3,4,5)
    list.map(i => i+addedValue).foreach(println)

    println("================")

    var totalTime = 0
    list.map(t => totalTime += t)
    println(totalTime)


//    closureVChanged()
  }

  def closureVChanged(): Unit ={

    //闭包在绑定了一个自由变量后，这个自由变量发生了变化
    var v = 100
    var sum = (x:Int) => x+v
    println(sum(1))

    v=1000
    println(sum(1))
  }

}
