package com.tencent.cubeli.sparksql.json

import org.apache.spark.sql.SparkSession

/**
  * Created by waixingren on 08/03/2018.
  */
object jsonnest {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder.master("local").appName("example").getOrCreate()
    val json = sparkSession.read.json("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/json/jsonnestdata.json")
    json.printSchema()
    json.show()
  }
}
