package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object Sample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("coalesce")
    val sc = new SparkContext(conf)

    sc.textFile("file:///Users/liyong/software/sparktraining/data/nation.tbl").sample(false, 0.8, 1000).collect().foreach(println)
  }

}
