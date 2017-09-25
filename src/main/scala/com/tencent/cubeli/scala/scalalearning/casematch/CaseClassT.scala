package com.tencent.cubeli.scala.scalalearning.casematch

object CaseClassT {


  def main(args: Array[String]): Unit = {


    val a:Any = HadoopPartition("hdfs://192.168.12.11:9000/log/20150718",0, 2048)
    a match{

      case HadoopPartition(a1,b,c) => print("hadoop partition, path is: " + a1)
      case TextPartition(a1) => println("text partition")
    }
  }
}

case class HadoopPartition(hdfsPath:String, startOff:Long, endOff:Long)

case class TextPartition(localPath:String)


//abstract class Person
//case class Student(name:String,sno:Int) extends Person
//case class Teacher(name:String,tno:Int) extends Person
//case class None(name:String) extends Person
//
//object CaseClassTest extends App {
//  def caseClassMatch(p:Person) = p match{
//    case Student(name,sno) => println(name + " is a student,sno is:" + sno)
//    case Teacher(name,tno) => println(name + " is a teacher,tno is:" + tno)
//    case None(name) => println("None matched")
//  }
//
//  val p = Student("yy",20151214)
//  caseClassMatch(p)
//}