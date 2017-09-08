package com.tencent.cubeli.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * Created by waixingren on 9/8/17.
  */
object DataSetCreate2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("Spark SQL create dataset").config("spark.some.config.option", "some-value").getOrCreate()
    import spark.implicits._
    val ds = spark.read.json("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.json").as[Nation]
  }

}

case class Nation(nationkey:Long, name:String, regionKey:Long, comment:String)
