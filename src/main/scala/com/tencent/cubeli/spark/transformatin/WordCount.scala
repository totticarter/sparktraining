package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Hello world!
  *
  */
object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("yarn").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val sourcdRdd = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/lineitem.tbl");
//    val sourcdRdd = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/nation.tbl");
    sourcdRdd.flatMap(_.split("\\|")).map((_,1)).reduceByKey(_ + _).take(10).foreach(println)
    sc.stop()
  }
}