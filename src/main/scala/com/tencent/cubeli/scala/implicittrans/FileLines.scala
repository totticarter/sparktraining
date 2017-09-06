package com.tencent.cubeli.scala.implicittrans

import java.io.{BufferedReader, File, FileReader}

/**
  * Created by waixingren on 8/30/17.
  * http://www.jianshu.com/p/a344914de895
  * http://blog.csdn.net/yuan_xw/article/details/49619441
  * http://blog.csdn.net/bluishglc/article/details/50866314
  */
object FileLines {


  def main(args: Array[String]): Unit = {


    /* 我期望可以像操作集合那样来操作一个文件中的所有行。比如，对所有的行映射（map）一个指定函数。
    ＊ 这个场景不是很理解
    implicit def file2Array(file: File): Array[String] = file.lines

    def map[R](source: Array[String])(fn: String ⇒ R) = {
      source.map(fn)
    }
    map(new File("/Users/waixingren/bigdata-java/spark/sparkproj/data/nation.tbl"))(println)
    */

  }

  implicit class Files(file: File) {
    def lines: Array[String] = {
      val fileReader: FileReader = new FileReader(file)
      val reader = new BufferedReader(fileReader)
      try {
        var lines = Array[String]()
        var line = reader.readLine()

        while (line != null) {
          lines = lines :+ line
          line = reader.readLine()
        }
        lines
      } finally {
        fileReader.close()
        reader.close()
      }
    }

    def getlineNum(file:File):Int = {
      val fileReader = new FileReader(file)
      val reader = new BufferedReader(fileReader)
      var count:Int = 0
      var line = reader.readLine()
      while(line != null){
        count += 1
        line = reader.readLine()
      }
      reader.close()
      count
    }
  }

  //这里直接定义了一个java的File对象，这个File对象本来是没有lines方法的，但是scala会去搜索有没有隐式实现
  //这里可以通过封装File来实现，但这无疑就多了一个类，不利于管理
  private val file: File = new File("D:\\bigdata\\training\\sparktraining\\data\\nation.tbl")
  println(file.getlineNum(file))

//  file.lines foreach println

}
