package com.tencent.cubeli.spark.action

import com.tencent.cubeli.common._
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by waixingren on 8/27/17.
  */
object Reduce {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("reduce")
    val sc = new SparkContext(conf)

    //如果在方法定义时，把输入参数作为一个二元组，那么在调用时不能只写方法名，还要加上方法的参数
    sc.textFile(Config.nationtblLocalPath).map(_.split("\\|")).map(arr => arr(0).toInt).reduce((a,b)=> max((a,b)))

    //如果在方法定义时，把输入参数作为两个分开的参数，则在调用时可以直接写方法名
    sc.textFile(Config.nationtblLocalPath).map(_.split("\\|")).map(arr => arr(0).toInt).reduce(sumFunc)
    //sc.textFile(Config.nationtblLocalPath).map(_.split("\\|")).map(arr => arr(0).toInt).reduce(a => max(a)))
    //sc.textFile(Config.nationtblLocalPath).map(_.split("\\|")).map(arr => arr(0).toInt).reduce(a => sumFunc2(a))
  }

  def sumFunc(a:Int, b: Int):Int = {
    a+b
  }

  //参考reduceByKey的方法传入一个二元祖，发现不行
  def sumFunc2(a:(Int, Int)):Int = {

    a._2+a._1
  }

  def max(thisandnext:(Int, Int)):Int = {

    if(thisandnext._1 >= thisandnext._2){
      thisandnext._1
    }else{
      thisandnext._2
    }
  }
}
