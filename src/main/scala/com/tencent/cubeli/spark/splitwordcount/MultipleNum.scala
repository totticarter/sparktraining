package com.tencent.cubeli.spark.splitwordcount

import org.apache.spark.rdd.RDD

/**
  * Created by waixingren on 8/26/17.
  */
class MultipleNum(flag: Boolean) {

  private val times1 = 1;
  private val times2 = 2;

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
