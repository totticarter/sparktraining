package com.tencent.cubeli.sparkmllib.decisiontree
import com.tencent.cubeli.common.SparkUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}
/**
  * Created by waixingren on 21/01/2018.
  * 用来查看训练数据向量化后的效果
  */
object Test {

  def main(args: Array[String]): Unit = {

    val sc = SparkUtil.getSparkContext()
    val data1 = sc.textFile("/Users/waixingren/bigdata-java/spark/sparkproj/data/mllib/trainingtree.txt")
    val tree1 = data1.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }
    tree1.collect.foreach(println)
  }
}
