package com.tencent.cubeli.sparksql
import scala.io.Source
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object DatasetCreate {

  def main(args: Array[String]): Unit = {


//    var nationRowSet:Seq[Array[String]] = new Seq[Array[String]]
//    val nationLines = Source.fromFile("/Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl")
//    for(line <- nationLines.getLines()){
//      nationRowSet += line.split("\\|")
//    }
//    val caseClassDS = Seq(Nation(1,"China",1,"it is a country")).toDS()
//
//

    val sparkSession = SparkSession.builder.master("local").appName("example").getOrCreate()

//    val spark = SparkSession.builder().appName("Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()


    import sparkSession.implicits._
    val data = sparkSession.read.text("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl").as[String]

    val words = data.flatMap(value => value.split("\\|"))
    val groupedWords = words.groupByKey(_.toLowerCase)


    case class Person(name: String, age: Long)
    var rawValue = Seq(Person("Andy",32))

    //报错
//    rawValue += new Person("liyong", 32)
//    val caseClassDS = Seq(Person("Andy", 32)).toDS()
//    caseClassDS.show()
  }
}

//case class Nation(nationkey:Long, nationName:String, regionKey:Long, comment:String)
