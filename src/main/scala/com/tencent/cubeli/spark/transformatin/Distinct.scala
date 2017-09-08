package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/26/17.
  */
object Distinct {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val data = Array(1, 1, 3, 4, 5)
    val distData = sc.parallelize(data)
    distData.distinct(1).collect.foreach(println)
  }

}
