package com.tencent.cubeli.sparksql.aggregate
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.expressions.MutableAggregationBuffer
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
/**
  * Created by waixingren on 9/8/17.
  */
object AggregateNoCheck {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()


    spark.udf.register("myAverage", AverageForDF)
    val df = spark.read.json("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.json")
    df.printSchema()
    df.createOrReplaceTempView("Nation")

    val result = spark.sql("select myAverage(regionkey) from Nation")
    result.show()
  }
}


object AverageForDF extends UserDefinedAggregateFunction {
  def inputSchema: StructType = StructType(StructField("inputColumn", LongType) :: Nil)
  def bufferSchema: StructType = {
    StructType(StructField("sum", LongType) :: StructField("count", LongType) :: Nil)
  }
  def dataType: DataType = DoubleType
  def deterministic: Boolean = true
  def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L
    buffer(1) = 0L
  }
  def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if (!input.isNullAt(0)) {
      buffer(0) = buffer.getLong(0) + input.getLong(0)
      buffer(1) = buffer.getLong(1) + 1
    }
  }
  def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
  }
  def evaluate(buffer: Row): Double = buffer.getLong(0).toDouble / buffer.getLong(1)
}