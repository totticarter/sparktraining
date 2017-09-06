package com.tencent.cubeli.scala.implicittrans

/**
  * Created by waixingren on 8/30/17.
  * They must be defined inside of another trait/class/object.
  * They may only take one non-implicit argument in their constructor.There may not be any method, member or object in scope with the same name as the implicit class.
Note: This means an implicit class cannot be a case class.
隐式类与旧的隐式转换的语法（implicit def）是有细微的不同的，隐式类的运作方式是：隐式类的主构造函数只能有一个参数（有两个以上并不会报错，但是这个隐式类永远不会被编译器作为隐式类在隐式转化中使用），且这个参数的类型就是将要被转换的目标类型。从语义上这很自然：这个隐式转换类将包裹目标类型，隐式类的所有方法都会自动“附加”到目标类型上。
  */

/**
  * 隐式类有如下几个限制:
  * They must be defined inside of another trait/class/object.
  * They may only take one non-implicit argument in their constructor.
  * There may not be any method, member or object in scope with the same name as the implicit class.
  * Note: This means an implicit class cannot be a case class.
  * 隐式类的运作方式：
  * 隐式类的主构造函数只能有一个参数（有两个以上并不会报错，但是这个隐式类永远不会被编译器作为隐式类在隐式转化中使用）
  * 且这个参数的类型就是将要被转换的目标类型
  * 隐式转换类将包裹目标类型，隐式类的所有方法都会自动"附加"到目标类型上
  *
  * @param origin 隐式类构造函数参数
  */
object ImplicitClass {


  def main(args: Array[String]): Unit = {

    //这种方法验证失败,导入了隐式类后max还是报错
    import com.tencent.cubeli.scala.implicittrans.ImplicitClass.ImplicitClassImpl
//    val m = math.max("1", 2)
  }

  implicit  class ImplicitClassImpl(str:String){

    def str2Int(str:String):Int = {

      str.toInt
    }
  }


  //转换类型为期望的类型
  implicit def double2Int(d: Double) = d.toInt
  val i:Int = 3.5





  //新类型操作
  case class Rational(n: Int, d: Int) {
    def +(r: Rational) = Rational(n + r.n, d + r.d)
  }

  implicit def int2Rational(v: Int) = Rational(v, 1)
  Rational(1, 1) + Rational(1, 1)
  1 + Rational(1, 1) //此时scala会去查找和Rational相关的隐式转换，处理方式是把一个int类型转换成Rational类型并返回，而rationnal类型是有＋号定义的方法的


}
