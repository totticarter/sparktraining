package com.tencent.cubeli.spark

import com.tencent.cubeli.common.SparkUtil
import org.apache.spark.util.sketch.CountMinSketch
import org.apache.spark.util.sketch.CountMinSketchImpl

/**
  * Created by waixingren on 25/02/2018.
  */
object countminsketch {


  def main(args: Array[String]): Unit = {

    val countMinSketch = CountMinSketch.create(0.1, 0.1, 10)
    countMinSketch.add("liyong")
    countMinSketch.add("li")
    countMinSketch.add("li")
    countMinSketch.add("yong")
    countMinSketch.add("yong")
    println(countMinSketch.estimateCount("li"))
    println(countMinSketch.totalCount())
  }
}
