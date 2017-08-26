package com.tencent.cubeli.spark

import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable.ArrayBuffer

/**
  * Created by waixingren on 8/26/17.
  */
object MapAndMapPartitioin {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("yarn").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val num = sc.parallelize((1 to 9), 3)
    num.map(num => doubleFuncForMap(num)).collect.foreach(println)
    num.mapPartitions(numiter => doubleFuncForMapPartition(numiter)).collect.foreach(println)
    sc.stop()
  }

  def doubleFuncForMap(num:Int):(Int, Int) = {

    (num, num*2)
  }

  def doubleFuncForMapPartition(iter: Iterator[Int]):Iterator[(Int, Int)] = {

    val array = new ArrayBuffer[(Int, Int)](10)
    while(iter.hasNext){

      val value:Int = iter.next()
//      array += (value, value*2) 如果使用单括号，scala无法判断到底应该是插入一个值还是多个值
      array += ((value, value*2))
    }
    array.iterator
  }


}
