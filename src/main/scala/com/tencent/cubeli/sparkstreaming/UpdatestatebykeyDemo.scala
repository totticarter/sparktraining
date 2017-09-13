package com.tencent.cubeli.sparkstreaming
import org.apache.log4j.{Level, Logger}
import org.apache.spark._
import org.apache.spark.streaming._
/**
  * Created by waixingren on 9/10/17.
  */


/*
*When running a Spark Streaming program locally, do not use “local” or “local[1]” as the master URL.
* Either of these means that only one thread will be used for running tasks locally. If you are using
* a input DStream based on a receiver (e.g. sockets, Kafka, Flume, etc.), then the single thread will
* be used to run the receiver, leaving no thread for processing the received data. Hence, when running
 * locally, always use “local[n]” as the master URL where n > number of receivers to run (see Spark
 * Properties for information on how to set the master). Extending the logic to running on a cluster,
 * the number of cores allocated to the Spark Streaming application must be more than the number of
 * receivers. Otherwise the system will receive data, but not be able to process them.
*
*
 */
object UpdatestatebykeyDemo {

  def main(args: Array[String]): Unit = {

    Logger.getRootLogger.setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("test").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("file:///Users/waixingren/bigdata-java/spark/sparkproj/checkpoint")
    val lines = ssc.socketTextStream("192.168.1.102",12345)
    val data = lines.flatMap(_.split(" "))
    val wordDstream = data.map(x => (x,3))


    val initialRDD = ssc.sparkContext.parallelize(List(("page1", 0.00)))
//    val stateDstream = wordDstream.updateStateByKey[Double](updateFuncDouble2,
//      new HashPartitioner(ssc.sparkContext.defaultParallelism), true, initialRDD)
val stateDstream = wordDstream.updateStateByKey[Int](updateFunc)
    stateDstream.print(10)
    ssc.start()
    ssc.awaitTermination()
  }

  val updateFunc = (values : Seq[Int],state : Option[Int]) => {
    val currentCount  = values.foldLeft(0)(_+_)
    val previousCount = state.getOrElse(0)
    Some(currentCount + previousCount)
  }

//  val updateFuncDouble = (values : Seq[Double],state : Option[Double]) => {
//
////    val sum = values.sum
////    Some(sum + state.get)
//
//    val currentCount  = values.foldLeft(0.0)(_+_)
//    val previousCount = state.getOrElse(0)
//    Some(currentCount + previousCount)
//  }

  val updateFuncDouble2 = (iterator: Iterator[(String, Seq[Double], Option[Double])]) => {
    iterator.flatMap(t => {
      val newValue: Double = t._2.sum
      val stateValue: Double = t._3.getOrElse(0);
      Some(newValue + stateValue)
    }.map(sumedValue => (t._1, sumedValue)))
  }

}
