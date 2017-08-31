package com.tencent.cubeli.spark.SharedVar

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object AccumulatorTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("accumulator")
    val sc = new SparkContext(conf)

    val totalPrice = sc.doubleAccumulator("totalprice")//在driver端定义一个累加器
    val itemPrices = sc.parallelize(List(23.4, 58.6,21.0, 90.3, 19.3))

    itemPrices.foreach(price => totalPrice.add(price))

    println(totalPrice.value)


    //使用实际数据进行演示
    sc.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").
      map(line => line.split("\\|")(0).toDouble).foreach(singlePrice => totalPrice.add(singlePrice))
    println(totalPrice.value)
  }
}
