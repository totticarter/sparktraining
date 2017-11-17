package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 13/11/2017.
  */
object Join2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("Join")
    val sc = new SparkContext(conf)

    val rawData1 = List((1,12345),(1,23445),(3,18987))
    val rawDataRdd1 = sc.parallelize(rawData1,1)

    val rawData2 = List((1,12345),(1,23445),(3,18987))
    val rawDataRdd2 = sc.parallelize(rawData2,2)

    rawDataRdd1.join(rawDataRdd2).collect.foreach(println)
  }
}
