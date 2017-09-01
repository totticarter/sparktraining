package com.tencent.cubeli.spark.transformatin

import org.apache.spark.{SparkConf, SparkContext}

object GroupByKey {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("groupbykey")
    val sc = new SparkContext(conf)

    //parallelize的方法原型是传入seq类型，所以只能构造出类型是二元组的array，而不能使用Map
    val x = sc.parallelize(Array(("a", 1), ("b", 1), ("a", 2),("a", 1), ("b", 1), ("b", 5),("b", 3), ("b", 1)), 3)
    x.groupByKey(1).collect.foreach(println)
    sc.stop()
  }
}
