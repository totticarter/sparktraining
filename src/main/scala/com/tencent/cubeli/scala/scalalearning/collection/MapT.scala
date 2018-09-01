package com.tencent.cubeli.scala.scalalearning.collection

import scala.collection.immutable.TreeMap

object MapT {

  def main(args: Array[String]): Unit = {

    var ip2Hostname = Map("192.168.11.11" -> "dnsserver", "192.168.2.11" -> "ftpserver", "192.168.34.21"-> "sshd server")
    ip2Hostname.foreach(println)
    ip2Hostname.foreach(a => println(a._1 + "          " + a._2))

    val op1 = Some(Array(1,2,3))
    val op2 = Some(Array(1,2,3, 4))
//    val op = op1.map(_.length>4).getOrElse{
//      op2.flatMap.
//    }
//    op

    //get
    println(ip2Hostname("192.168.11.11"))

    //add
    ip2Hostname += ("a" -> "b")
    ip2Hostname.foreach(println)

    //contains
    ip2Hostname.contains("a")

    //remove
    ip2Hostname -= ("a")
    ip2Hostname.foreach(println)

    val keys = ip2Hostname.keys
    val vals = ip2Hostname.values
    vals.foreach(println)


    //=======================================================
    var treeMap = new TreeMap[String, Int]()
    treeMap += ("one" -> 1)



  }

}
