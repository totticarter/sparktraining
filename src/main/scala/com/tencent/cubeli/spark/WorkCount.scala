package com.tencent.cubeli
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Hello world!
  *
  */
object App {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("yarn").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val sourcdRdd = sc.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/pom.xml");
    sourcdRdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _).collect().foreach(println)
    sc.stop()
  }
}