package com.tencent.cubeli.sparksql
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession


object DataFrameCreate {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()

    import spark.implicits._
    val df = spark.read.json("file:///Users/liyong/software/sparktraining//data/nation.json")

//    println("=========group by count with dataframe===================")
//    df.
//      filter($"nationkey"<9).
//      groupBy("regionkey").
//      count.
//      show()

    df.filter($"nationkey"<7).groupBy("regionkey").sum("nationkey").queryExecution.debug.codegen();
    df.filter($"nationkey"<7).groupBy("regionkey").sum("nationkey").show();

    //
//    println("=========print all nation dataframe lines===================")
//
//    df.show()
//
//    println("=========print out nation dataframe schema===================")
//    df.printSchema()
//
//    println("=========print out nationkey in dataframe===================")
//    df.select("nationkey").show()
//
//    println("=========print out row which nationkey > 9 in dataframe===================")
//    df.filter($"nationkey">9).show()


    //============================通过创建临时表查询========================================
    //df 这个dataframe来自spark这个sparksession，df注册了一个临时表以后，spark这个session就知道有一个和df关联的临时表nation
//    df.createTempView("nation")
//    spark.
//      sql("select count(*) from nation where nationkey>9 group by regionkey").
//      show()




  }

}
