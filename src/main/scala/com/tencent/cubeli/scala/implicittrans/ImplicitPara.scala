package com.tencent.cubeli.scala.implicittrans

/**
  * Created by waixingren on 8/30/17.
  * 为什么把隐式参数单独拿出来放到最后讲是因为从用意上讲，隐式参数与我们前面讲述的隐式类型转化有很大的差异，
  * 虽然它涉及到了关键字implict，但是它做的是另外一件事情。隐含参数有点类似缺省参数，如果在调用方法时没有
  * 提供某个参数，编译器会在当前作用域查找是否有符合条件的 implicit 对象可以作为参数传入，不同于缺省参数，
  * 隐式参数的值可以在方法调用的前的上下文中指定，这是隐式参数更加灵活的地方。
  */

/**
  * 隐式参数
  * 隐式参数一般和柯里化进行结合,使用该函数不用给出implicit的值
  *
  * @param param
  * @param impl
  */

object ImplicitPara {

  object Greeter{
    def greet(name:String)(implicit prompt: String) {
      println("Welcome, " + name + ". The System is ready.")
      println(prompt)
    }
  }

  def main(args: Array[String]) {

    implicit val prompt = ">"

    Greeter.greet("admin")
  }

}
