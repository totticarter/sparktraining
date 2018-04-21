package com.tencent.cubeli.sparksql

import com.tencent.cubeli.common.Config
import org.apache.spark.{SparkConf, SparkContext}

object RddSimulateSql {

  def main(args: Array[String]): Unit = {

    //select count(*) from nation where nationkey>9 group by regionkey
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("rdd simulate sql"))
    sc.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").
      map(line => line.split("\\|")).
      filter(array => array(0).toInt<9).
      map(array => (array(2),1)).
      reduceByKey(_+_).
      collect.
      foreach(println)
//aaa

  }

}
