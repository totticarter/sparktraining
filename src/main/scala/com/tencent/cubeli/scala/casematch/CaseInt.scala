package com.tencent.cubeli.scala.casematch

object CaseInt {

  def main(args: Array[String]): Unit = {

    //如果匹配到，则打印对应的星期；如果没有匹配到，用一个随便的变量就可以表示这个异常值
    val dayFlag:Int = 4
    dayFlag match {
      case 1 => println("Monday")
      case 2 => println("Tursday")
      case 3 => println("Wensday")
      case 4 => println("T")
      case which => println(which.toString)
    }

    //上例中，每个case后都要打印，但是如果能返回一个值，那么只需要调用一次打印即可
    val matchedValue = dayFlag match{
      case 1 => "Monday"
      case 2 => "Tursday"
      case 3 => "Wensday"
      case 4 => "T"
      case which => println(which.toString)
    }
    println(matchedValue)

    //演示字符串匹配
    val targetIp = "192.168.10.16"
    val matchedHostname = targetIp match {
      case "192.168.10.10" => "DNS_SERVER"
      case "192.168.10.11" => "FTP_SERVER"
      case "192.168.10.12" => "SSH_SERVER"
      //case aaa => "not matched"
        // 如果缺省项写一个任意的变量，那么调用这个变量的tostring方法，可以获取到这个值，如果写一个下划线
        //表示不关心缺省值具体是什么
      case _ => "not matched"
    }
    println(matchedHostname)


    //类型匹配
    val s:Any = Map(1 -> "one")
    val len:Int = s match {

      case strVal:String => strVal.length
      case intVal:Int => intVal
      case map:Map[_, _] => map.size
    }
    println("类型匹配测试")
    println(len)

    //演示匹配多种不同的类型
    //val v:Any = "six"
    val v:Any = ("10.11.111.34", 10)
    val matchedV = v match {

      case 10 => "ten"
      case "six" => 6
      case 2.3 => "two point three"
      case (key, value) => (key, value)
      case _ => "not matched"
    }

    println(matchedV.toString)

  }
}
