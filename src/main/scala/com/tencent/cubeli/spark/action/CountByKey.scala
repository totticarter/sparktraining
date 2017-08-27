package com.tencent.cubeli.spark.action

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object CountByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)

    val x = sc.parallelize(Array(("a", 1), ("b", 1), ("a", 2),("a", 1), ("b", 1), ("b", 5),("b", 3), ("b", 1)), 3).countByKey()
  }
}
