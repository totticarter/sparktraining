package com.tencent.cubeli.spark.transformatin.splitwordcount

import com.tencent.cubeli.common.Config
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 9/1/17.
  * 和scala里的闭包进行比较，scala里的闭包可以修改自由变量，因为传递的是一个引用
  * 而spark中的闭 bao传递的则是一个拷贝值，所以这里的base值不会被修改
  */
object Closure {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("closure")
    val sc = new SparkContext(conf)

    var studentNumberBase = 100;
    val studentNumbers = List(41,45,23,98)
    sc.parallelize(studentNumbers).foreach(studentNumber => studentNumberBase += studentNumber)
    println(studentNumberBase)
    sc.stop()
  }

  def printlnOnExecutor(stutentNumber:Int, studentNumberBase:Int): Unit ={

//    studentNumberBase += stutentNumber
    //这里有编译错误，方法参数默认是val类型，这里给val类型赋值编译报错，但是如果在参数声明那里加上var，也编译报错
    //那么怎么在executor上打印这个studentNumber的值呢？
  }

}
