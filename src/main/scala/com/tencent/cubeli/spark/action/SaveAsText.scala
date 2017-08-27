package com.tencent.cubeli.spark.action

import com.tencent.cubeli.common.Config
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object SaveAsText {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster(Config.master).setAppName("saveastext")
    val sc = new SparkContext(conf)

    sc.textFile(Config.nationtblLocalPath).map(_.split("\\|")(3)).saveAsTextFile(Config.outputDirPath+"-comment.txt")
  }

}
