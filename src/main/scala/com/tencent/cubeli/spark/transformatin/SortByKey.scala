package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object SortByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("SortByKey")
    val sc = new SparkContext(conf)

    val rowData = sc.textFile("/Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").map(_.split("\\|")).map(v => (v(2),v)).sortByKey().collect.foreach(println)

  }
}
