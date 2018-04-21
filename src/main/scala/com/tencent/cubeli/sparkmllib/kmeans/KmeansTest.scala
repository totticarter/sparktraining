package com.tencent.cubeli.sparkmllib.kmeans

import com.tencent.cubeli.common.SparkUtil
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.param.Param
import org.apache.spark.sql.functions._

//for spark 2.3.0
//import org.apache.spark.ml.evaluation.ClusteringEvaluator
import org.apache.spark.sql.SparkSession
/**
  * Created by liyong on 20/03/2018.
  */
object KmeansTest {


  def main(args: Array[String]) {


    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()
    val dataset = spark.read.format("libsvm").load("data/mllib/sample_kmeans_data.txt")


    dataset.show(4)
    dataset.select(col("features")).rdd.collect().foreach(println)
    val trainDataset = dataset.sample(false, 0.8, 100)
    println(trainDataset.count())
    // Trains a k-means model.
    val kmeans = new KMeans().setK(2).setSeed(1L)
    val model = kmeans.fit(dataset)

    // Evaluate clustering by computing Within Set Sum of Squared Errors.
    val WSSSE = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    // Shows the result.
    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)
  }
}
