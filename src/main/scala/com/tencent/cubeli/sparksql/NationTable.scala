package com.tencent.cubeli.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by waixingren on 8/26/17.
  */
object NationTable {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("nation")
    val sc = new SparkContext(conf)


    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._


    val nationSchemaMap = getNationSchemaArray("/Users/waixingren/bigdata-java/spark/sparkproj/data/nationschema.data")
    val fields = nationSchemaMap.map(filedInfo => getStructField(filedInfo))
    val schema = StructType(fields)


    val nationRddLineSplited = spark.sparkContext.textFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").map(line => line.split("\\|"))
    val rowRDD = nationRddLineSplited.map(attributes => Row(attributes(0).trim.toLong, attributes(1).trim, attributes(2).trim.toLong, attributes(3).trim))
    val nationDF = spark.createDataFrame(rowRDD, schema)
    nationDF.select("nationkey").show
    spark.stop()

  }

  def getStructField(fieldInfo: String):StructField = {

    val typeMap = getTypeMap()
    val array:Array[String] = fieldInfo.split(" ")
    val sparsqlType:DataType = typeMap(array(1))
    new StructField(array(0), sparsqlType, nullable = true)
  }


  def getNationSchemaArray(schemaPath: String):Array[String] = {

    var fieldsInfo = new ArrayBuffer[String]()
    val fileLines = Source.fromFile(schemaPath)
    for(line <- fileLines.getLines()){
      fieldsInfo += line
    }
    fieldsInfo.toArray
  }

  def getTypeMap():Map[String, DataType] = {

    var typeMap:Map[String, DataType] = Map()
    typeMap += ("string" -> StringType)
    typeMap += ("long" -> LongType)
    typeMap += ("int" -> IntegerType)
    typeMap += ("double" -> DoubleType)

    typeMap
  }

}
