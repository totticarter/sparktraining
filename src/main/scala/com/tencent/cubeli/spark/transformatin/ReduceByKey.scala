package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/26/17.
  */
object ReduceByKey{

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("yarn").setAppName("get max")
    val sc = new SparkContext(conf)

    val x = sc.parallelize(Array(("a", 1), ("b", 1), ("a", 2),("a", 1), ("b", 1), ("b", 5),("b", 3), ("b", 1)), 3)
    x.reduceByKey((a,b) => max((a,b))).collect.foreach(println)

  }

  def max(thisandnext:(Int, Int)):Int = {

    println("get lagger one")
    if(thisandnext._1 >= thisandnext._2){
      thisandnext._1
    }else{
      thisandnext._2
    }
  }

  def max2(a:Int, b:Int):Int = {

      a
  }
}
