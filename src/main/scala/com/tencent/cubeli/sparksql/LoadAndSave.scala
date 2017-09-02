package com.tencent.cubeli.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object LoadAndSave {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    import spark.implicits._

    val orderDF = spark.read.load("orders.parquet")
    orderDF.
      select("orderkey", "custkey").
      write.
      save("twocolumn.parquet")
  }

}
