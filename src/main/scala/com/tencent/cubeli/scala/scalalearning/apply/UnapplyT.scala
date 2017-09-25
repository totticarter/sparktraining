package com.tencent.cubeli.scala.scalalearning.apply

/**
  * Created by cubeli on 2017/9/25.
  * unapply是提取器，用于模式匹配，在用户定义一个样例类时，scala会自动生成一个unapply方法
  * scala在编译时会判断case后的类是否有unapply方法，如果没有就会编译报错，随意有两种方法：
  * 1.定义一个case类
  * 2.定义一个普通类，然后在这个类的伴生对象中实现unapply方法
  *
  * 如果你有一个类型不确定的对象，你可以同时检查其类型并解构。
  * 下述代码中，如果FreeUser和PremiumUser没有加case关键字，那么就需要实现unapply方法
  * 在进行类型配置时，首先尝试调用FreeUser的unapply方法，发现入参类型不匹配，则尝试调用PremiumUser的unapply方法，参数类型匹配则执行
  * 可以把FreeUser的unapply方法的入参改成PremiumUser类型，会发现match上的是FreeUser(name)
  */
object UnapplyT {

  def main(args: Array[String]): Unit = {

    val user: User = new PremiumUser("Daniel")
    user match {
      case FreeUser(name) =>  "Hello" + name
      case PremiumUser(name) =>  "Welcome back, dear" + name
    }
    println(user.name)
  }


  trait User {
    def name: String
  }
  class FreeUser(val name: String) extends User
  class PremiumUser(val name: String) extends User

  object FreeUser {
    def unapply(user: FreeUser): Option[String] = {
      Some(user.name)
    }
  }
  object PremiumUser {
    def unapply(user: PremiumUser): Option[String] = {
      Some(user.name)
    }
  }

}
