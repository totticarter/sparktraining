package com.tencent.cubeli.sparksql.aggregate
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.SparkSession

case class Nation(nationkey:Long, name:String, regionKey:Long, comment:String)
case class Average(var sum: Long, var count: Long)

/**
  * Created by waixingren on 9/8/17.
  */
object AggregateExam {

}

object MyAverage extends Aggregator[Nation, Average, Double] {
  def zero: Average = Average(0L, 0L)
  def reduce(buffer: Average, nation: Nation): Average = {
    buffer.sum += nation.nationkey
    buffer.count += 1
    buffer
  }
  def merge(b1: Average, b2: Average): Average = {
    b1.sum += b2.sum
    b1.count += b2.count
    b1
  }
  def finish(reduction: Average): Double = reduction.sum.toDouble / reduction.count
  def bufferEncoder: Encoder[Average] = Encoders.product
  def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

object Main{

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("Spark SQL自定义聚合函数").config("spark.some.config.option", "some-value").getOrCreate()
    import spark.implicits._
    val ds = spark.read.json("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.json").as[Nation]

    val averageNationkey = MyAverage.toColumn.name("averageNationkey")
    val result = ds.select(averageNationkey)
    result.show()
  }
}
