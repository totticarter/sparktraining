package com.tencent.cubeli.spark.transformatin.splitwordcount

import org.apache.spark.rdd.RDD

/**
  * Created by waixingren on 8/26/17.
  */
class MultipleNum(flag: Boolean) {

  private val times1:Int = 1;//不管这里是否明确指定times1的类型，都会报任务无法序列化的错
  private val times2:Int = 2;

  def doMap(rdd: RDD[String]):RDD[(String, Int)] = {

    if(flag){

      rdd.map(word => mapFunc2(word))
    }else{

      rdd.map(word => mapFunc1(word))
    }
  }

  def mapFunc1(input:String) = {

    (input, times1)
  }

  def mapFunc2(input:String) = {
    (input, times2)
  }


}
