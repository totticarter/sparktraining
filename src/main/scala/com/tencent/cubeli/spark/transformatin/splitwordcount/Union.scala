package com.tencent.cubeli.spark.transformatin.splitwordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/26/17.
  */
object Union {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("nation")
    val sc = new SparkContext(conf)

    val rdd1 = sc.textFile("/Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl")
    val rdd2 = sc.textFile("/Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl")

    rdd1.union(rdd2).collect.foreach(println)
  }

}
