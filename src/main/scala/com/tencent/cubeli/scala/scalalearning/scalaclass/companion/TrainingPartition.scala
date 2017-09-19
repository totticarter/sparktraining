package com.tencent.cubeli.scala.scalalearning.scalaclass.companion

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

  //实例对象访问静态成员
  def tryToUseObjectVar(): Unit ={
    TrainingPartition.idx
  }
}

//单例对象和类同名，那么这个单例对象叫做类的伴生对象，像不像工厂模式？
object TrainingPartition {

  var idx:Int = 0
  def apply(partitionNum: Int, path: String, tableName: String): TrainingPartition = {


    new TrainingPartition(partitionNum, path, tableName)

  }

  //静态成员访问实例,无法访问，因为还没有实例化出来
  def tryToUseInstanceVar(): Unit ={

  }

  def increaseIdx(): Unit ={
    idx += 1
  }

  val partitionName:String = "trainingpartition"
}

object Main{

  def main(args: Array[String]): Unit = {

    //这里并没有使用new
    val trainingPartition = TrainingPartition(1,"hdfs://localhost:9000/user/data", "traction_log")
    TrainingPartition.increaseIdx()
    TrainingPartition.idx

    trainingPartition.tryToUseObjectVar()
    //这样是不行的，因为idx属于类，而不是一个具体实例
//    trainingPartition.idx
    println(trainingPartition.path)
  }
}
