package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object AggregateByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("aggretatebykey")
    val sc = new SparkContext(conf)

    val data = List((1,3),(1,4),(1,3),(3,3),(2,2),(4,4),(4,5))
    //val rdd = sc.parallelize(data,1).aggregateByKey(10000)(concatPart, concatFinal).collect.foreach(println)
    val rdd = sc.parallelize(data,1).aggregateByKey("10000")(concatPart, concatFinal).collect.foreach(println)
//    zerovalue Int, seqOp(Int,Int) => Int, compOp (Int, Int) => Int
//    zerovalue String, seqOp(String, Int) => String, compOp (String, String) => String

    val zero = collection.mutable.Set[Int]()
    sc.parallelize(data,1).aggregateByKey(zero)((set, v) => set += v, (set1, set2) => set1 ++= set2)


    //使用reducebykey实现
    sc.parallelize(data,1).map(kv => (kv._1, collection.mutable.Set[Int]() + kv._2))
      .reduceByKey(_ ++ _)
  }


  //函数的参数不能交换顺序
  def concatPart(a:String, b: Int):String = {

    a.toString + b.toString

  }

  def concatFinal(a:String, b:String):String = {

    a+b
  }

}
