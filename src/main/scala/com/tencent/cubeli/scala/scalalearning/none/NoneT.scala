package com.tencent.cubeli.scala.scalalearning.none

/**
  * Created by cubeli on 2017/9/25.
  * None是一个object，是Option的子类型，定义如下
  * scala推荐在可能返回空的方法使用Option[X]作为返回类型。如果有值就返回Some[x](Some也是Option的子类)，否则返回None
  * 获得Option后，可以使用get获得包含的值，或者使用getOrElse获得默认值如果isEmpty为true。
  */
object NoneT {

  def main(args: Array[String]): Unit = {

    val map = Map(1->"one", 2->"two", 3->"three")
    val a = get(map)
    println(a.getOrElse("four"))

  }

  //这里的入参Map如果不写明kv的类型，会报错
  def get(map: Map[Int, String]): Option[String] = {
    if (map.contains(4))
      Some(map(1))
    else
      None
  }

}
