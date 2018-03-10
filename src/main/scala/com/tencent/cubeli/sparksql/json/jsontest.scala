
package com.tencent.cubeli.sparksql.json

import java.text.MessageFormat

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SparkSession, functions}


class PersisitWifiUserBehavior  extends Serializable{

  @transient
  val logger = Logger.getLogger(classOf[PersisitWifiUserBehavior])
  Logger.getLogger("org.apache").setLevel( Level.ERROR )

  val dataSplitKey = "\001"

  var sdkNetWorkLogTemplate = "file:///Users/waixingren/bigdata-java/spark/sparkproj/data/json/jsontest.json"

  def saveAsWifiUserBehavior( year:String , month:String ,day:String): Unit ={

    val sparkSession = SparkSession.builder().appName("PersisitWifiUserBehavior").master("local").getOrCreate()

    val sdkNetWorkLogFilePathOfQD =  MessageFormat.format(sdkNetWorkLogTemplate, "qd",year,month,day )


    /**以下操作均为解析联网SDK数据，基于不同业务转为多张事实表*/
    //1、基于SDK联网数据中的_lkey为data-uplogdata的数据集获取用户的开网次数数据。
    logger.info( sdkNetWorkLogFilePathOfQD )

    val qdSDKNetWorkDataRDD = sparkSession.read.textFile(sdkNetWorkLogFilePathOfQD).rdd.filter( line => ( line.contains("data-uplogdata") || line.contains("data-netcontrol") ) )
//    qdSDKNetWorkDataRDD.saveAsTextFile("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/json/tmp.json")
    //val qdSDKNetWorkDataRDD = sparkSession.read.textFile(sdkNetWorkLogFilePathOfQD).rdd
//    val tmpjson = sparkSession.read.json("file:///Users/waixingren/bigdata-java/spark/sparkproj/data/json/tmp.json")
//    tmpjson.printSchema()



    val qdSDKNetWorkDataDF  = sparkSession.read.json(qdSDKNetWorkDataRDD)
//    qdSDKNetWorkDataDF.printSchema()

    val df_lval = qdSDKNetWorkDataDF.select(qdSDKNetWorkDataDF("_lval"))
    df_lval.printSchema()
    df_lval.show()

    val ddata = df_lval.select("_ddata")
    ddata.printSchema()

//    val df_lval = qdSDKNetWorkDataDF.select(functions.explode(qdSDKNetWorkDataDF("_lval")))
//    df_lval.printSchema()
//    df_lval.show()

//    val df_ddata = df_lval.select(functions.explode(df_lval("_ddata")))
//    df_ddata.printSchema()
//    df_ddata.show()
  }

}

object PersisitWifiUserBehavior {
  def main(args: Array[String]): Unit = {
    val pwub = new PersisitWifiUserBehavior()
    pwub.saveAsWifiUserBehavior("2018","03","05")
    val year = "2018"
    val month = "03"
    val day = "05"
  }
}















