package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 9/7/17.
  */
object WordCountDemor {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val sourcdRdd = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/nation.tbl");
    sourcdRdd.flatMap(_.split("\\|")).map(word => (word,1)).reduceByKey((v1,v2) => v1+v2).take(10).foreach(println)
    sc.stop()
  }
}
