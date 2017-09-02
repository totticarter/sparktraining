package com.tencent.cubeli.spark.persist

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Persist {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("accumulator")
    val sc = new SparkContext(conf)

    val nationrdd = sc.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").
      map(line => line.split("\\|")).
      map(arr => (arr(2).toInt, arr(0)+ "---" + arr(1) + "---" + arr(3)))
    nationrdd.persist(StorageLevel.DISK_ONLY)
    //nationrdd.persist(StorageLevel.DISK_ONLY_2)
    //nationrdd.persist(StorageLevel.MEMORY_AND_DISK_2)
    //nationrdd.persist(StorageLevel.OFF_HEAP)

    val rawData = List((1,12345),(1,23445),(3,18987))
    val rawDataRdd = sc.parallelize(rawData)
    rawDataRdd.persist()

    nationrdd.join(rawDataRdd).collect.foreach(println)
  }
}
