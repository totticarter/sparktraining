package com.tencent.cubeli.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object AggregateByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("aggretatebykey")
    val sc = new SparkContext(conf)

    val data = List((1,3),(1,4),(1,3),(3,3),(2,2),(4,4),(4,5))
    val rdd = sc.parallelize(data,1).aggregateByKey("10000")(concatPart, concatFinal).collect.foreach(println)
  }


  def concatPart(a:String, b: Int):String = {

    a.toString + b.toString

  }

  def concatFinal(a:String, b:String):String = {

    a+b
  }

}
