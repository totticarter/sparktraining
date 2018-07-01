package com.tencent.cubeli.sparkmllib.kmeans

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

/**
  * Created by liyong on 20/03/2018.
  * http://blog.csdn.net/bitcarmanlee/article/details/52092288
  */
object KmeasTest2 {

  def main(args: Array[String]) {


    val conf = new SparkConf().setMaster("local").setAppName("df create")
    val sc = new SparkContext(conf)

    val rawTrainingData = sc.textFile("data/mllib/kmeasdata.csv")
    val parsedTrainingData =
      rawTrainingData.filter(!isColumnNameLine(_)).map(line => {
        Vectors.dense(line.split(",").map(_.trim).filter(!"".equals(_)).map(_.toDouble))
      }).cache()
    parsedTrainingData.collect().foreach(println)

    // Cluster the data into two classes using KMeans

    val numClusters = 8
    val numIterations = 30
    val runTimes = 3
    var clusterIndex: Int = 0
    val clusters: KMeansModel = KMeans.train(parsedTrainingData, numClusters, numIterations)
    clusters.save(sc, "file:///Users/liyong/software/sparktraining/data/mllib/kmeams")
    println("Cluster Number:" + clusters.clusterCenters.length)

    println("Cluster Centers Information Overview:")
    clusters.clusterCenters.foreach(
      x => {
        println("Center Point of Cluster " + clusterIndex + ":")
        println(x)
        clusterIndex += 1
      })

    //begin to check which cluster each test data belongs to based on the clustering result

    val rawTestData = sc.textFile("data/mllib/kmeasdata.csv")
    val parsedTestData = rawTestData.map(line => {
      Vectors.dense(line.split(",").map(_.trim).filter(!"".equals(_)).map(_.toDouble))

    })
    parsedTestData.collect().foreach(testDataLine => {
      val predictedClusterIndex:
      Int = clusters.predict(testDataLine)
      println("The data " + testDataLine.toString + " belongs to cluster " +
        predictedClusterIndex)
    })

    println("Spark MLlib K-means clustering test finished.")
  }

  private def isColumnNameLine(line: String): Boolean = {
    if (line != null && line.contains("Channel")) true
    else false
  }
//  }

}
