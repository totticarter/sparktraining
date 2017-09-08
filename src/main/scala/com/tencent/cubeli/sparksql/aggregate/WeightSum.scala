package com.tencent.cubeli.sparksql.aggregate
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.expressions.MutableAggregationBuffer
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
//演示自定义聚合函数，把nation的nationkey进行加权求和，加权值根据regionkey来确定
//没有调通，报错
//object Main{

//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setMaster("local").setAppName("df create")
//    val sc = new SparkContext(conf)
//    val spark = SparkSession
//      .builder()
//      .appName("Spark SQL basic example")
//      .config("spark.some.config.option", "some-value")
//      .getOrCreate()
//    spark.udf.register("weightSum", WeightSum)
//
//    val df = spark.read.json("D:\\bigdata\\training\\sparktraining\\data\\nation.json")
//    df.createOrReplaceTempView("nation")
//    df.show()
//
//    spark.sql("SELECT weightSum(nationkey)  FROM nation").show()
//  }
//}


object WeightSum extends UserDefinedAggregateFunction {
  override def inputSchema: StructType = StructType(StructField("inputColumn", LongType) :: Nil)

  override def bufferSchema: StructType = StructType(StructField("sum", LongType) :: Nil)
  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = buffer(0)=0L

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {

      val weight = input.getLong(0) match{

        case 0 => 1000
        case 1 => 2000
        case 2 => 3000
        case 3 => 4000
        case 4 => 5000
        case _ => 0
      }

    buffer(0) = buffer.getLong(0) + input.getLong(0) * weight
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit ={

    buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
  }

  override def evaluate(buffer: Row): Any = buffer.getLong(0)
}
