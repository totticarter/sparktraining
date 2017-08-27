package com.tencent.cubeli.spark.SharedVar

import com.tencent.cubeli.common.Config
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import org.apache.spark.broadcast.Broadcast

/**
  * Created by waixingren on 8/27/17.
  */
object BroadCastTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster(Config.master).setAppName("boradcast")
    val sc = new SparkContext(conf)
    val nationShared:Broadcast[Array[String]] = sc.broadcast(getNation(Config.nationtblHdfsPath))

    val rawData = List(1,2,3)
    sc.parallelize(rawData).map(value => mapFunc(nationShared, value)).collect.foreach(foreachFun)

  }

  def getNation(filePath: String):Array[String] = {


    val array:ArrayBuffer[String] = new ArrayBuffer[String]()
    val file = Source.fromFile(filePath)
    for(line <- file.getLines()){

      array += line
    }
    array.toArray
  }

  def mapFunc(nationShared:Broadcast[Array[String]], a:Int):Array[String] = {

      var r:ArrayBuffer[String] = new ArrayBuffer[String]()
      val nation:Array[String] = nationShared.value
      for(lint <- nation){

        r += lint+a.toString
      }

      r.toArray
  }

  def foreachFun(arr:Array[String]): Unit ={

    arr.foreach(println)
  }
}
