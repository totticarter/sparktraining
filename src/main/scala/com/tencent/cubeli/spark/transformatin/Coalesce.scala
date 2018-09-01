package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object Coalesce {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("coalesce")
    val sc = new SparkContext(conf)


    val data = List((1,3),(1,4),(1,3),(3,3),(2,2),(4,4),(4,5))
    val rdd = sc.parallelize(data,20).coalesce(10).collect().foreach(println)

//    sc.textFile("file:///Users/liyong/software/sparktraining/data/nation.tbl").filter(line => line.split("\\|")(2).toInt>2).coalesce(1).collect().foreach(println)
  }

}
