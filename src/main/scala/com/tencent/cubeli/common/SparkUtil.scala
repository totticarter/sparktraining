package com.tencent.cubeli.common

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by cubeli on 2017/9/15.
  */
object SparkUtil {

  def getSparkContext():SparkContext = {

    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)
    sc
  }
}
