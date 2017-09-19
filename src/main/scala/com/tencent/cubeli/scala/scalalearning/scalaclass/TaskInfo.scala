package com.tencent.cubeli.scala.scalalearning.scalaclass


object Main{

  def main(args: Array[String]): Unit = {

    val taskInfo = new TaskInfo("node1", "node2", 8988)
    println(taskInfo.toString)
    println(taskInfo.srcNode)

    taskInfo.srcNode = "node3"//演示var成员变量可被直接修改
    println(taskInfo.srcNode)
    println(taskInfo.destNode)

  }
}
class TaskInfo(var srcNode:String, val destNode:String, destPort:Int) {

  //初始化一个map，演示这个初始化会被执行
  val nodeMap = Map("node1" -> "192.168.101.11", "node2" -> "10.98.90.124", "node3" -> "127.0.0.1")
  def getIP(nodeName: String):String = {

    nodeMap(nodeName)
  }

  //演示重写默认方法
  override def toString: String = srcNode + " to " + destNode + ": " + destPort.toString

  println(getIP(srcNode))
  println(getIP(destNode))

  private val startTime = System.currentTimeMillis()
  println(startTime)
}
