package com.tencent.cubeli.scala.scalaclass.companion

import scala.collection.mutable.ArrayBuffer

/**
  * Created by waixingren on 8/31/17.
  */

//类名和单例对象同名，那么这个类叫伴生类
//如果这里不指定val或者var的话，不算做类的成员变量，类不会给这个变量自动生成getter和setter方法
class TrainingPartition(val partitionNum: Int, val path:String, val tableName:String){


  val array = ArrayBuffer(1,2,3)
  array += 4
  array.par
}

//单例对象和类同名，那么这个单例对象叫做类的伴生对象，像不像工厂模式？
object TrainingPartition {

  def apply(partitionNum: Int, path: String, tableName: String): TrainingPartition = {


    new TrainingPartition(partitionNum, path, tableName)

  }
}

object Main{

  def main(args: Array[String]): Unit = {

    //这里并没有使用new
    val trainingPartition = TrainingPartition(1,"hdfs://localhost:9000/user/data", "traction_log")
    println(trainingPartition.path)
  }
}
