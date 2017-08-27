package com.tencent.cubeli.spark.action

import java.io.{File, PrintWriter}

import com.tencent.cubeli.common.Config
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source

/**
  * Created by waixingren on 8/27/17.
  */
object ForEach {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster(Config.master).setAppName("saveastext")
    val sc = new SparkContext(conf)

    sc.textFile(Config.nationtblLocalPath).map(_.split("\\|")(3)).foreach(writeToFile)
  }

  def writeToFile(line:String): Unit ={

     val targetFile = new PrintWriter(new File(Config.outputDirPath + "foreach.txt"))
     targetFile.append(line)
  }

}
