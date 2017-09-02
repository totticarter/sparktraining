package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object Join {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("Join")
    val sc = new SparkContext(conf)

    val nationrdd = sc.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").
      map(line => line.split("\\|")).
      map(arr => (arr(2).toInt, arr(0)+ "---" + arr(1) + "---" + arr(3)))

    val rawData = List((1,12345),(1,23445),(3,18987))
    val rawDataRdd = sc.parallelize(rawData)

    nationrdd.join(rawDataRdd).collect.foreach(println)

  }
}
