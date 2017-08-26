package com.tencent.cubeli.spark.splitwordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/26/17.
  */
object MultipleNumMain {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val sourcdRdd = sc.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/pom.xml");
    val multipleNum = new MultipleNum(true)
    multipleNum.doMap(sourcdRdd).reduceByKey(_ + _).collect.foreach(println)

    sc.stop()
  }
}
