package com.tencent.cubeli.spark.tuning

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 9/7/17.
  */
object Cogroup {

  def main(args: Array[String]): Unit = {


    val conf = new SparkConf().setMaster("local").setAppName("aggretatebykey")
    val sc = new SparkContext(conf)

    val sourcdRdd1 = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/supplierbig.tbl").flatMap(line => line.split("\\|")).map(word => (word,1));
    val sourcdRdd2 = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/supplierbig.tbl").flatMap(line => line.split("\\|")).map(word => (word,1));
    val sourcdRdd3 = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/supplierbig.tbl").flatMap(line => line.split("\\|")).map(word => (word,1));

//    val sourcdRdd1 = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/part.tbl").flatMap(line => line.split("\\|")).map(word => (word,1));
//    val sourcdRdd2 = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/part.tbl").flatMap(line => line.split("\\|")).map(word => (word,1));
//    val sourcdRdd3 = sc.textFile("file:///Users/waixingren/software/tpch/tpch-dbgen/part.tbl").flatMap(line => line.split("\\|")).map(word => (word,1));

    //sourcdRdd1.join(sourcdRdd2).join(sourcdRdd3).take(10).foreach(println)
   val cogroupedRdd = sourcdRdd1.cogroup(sourcdRdd2, sourcdRdd3).take(10).foreach(println)

  }
}
