package com.tencent.cubeli.sparkmllib.decisiontree

import com.tencent.cubeli.common.SparkUtil
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.mllib.util.MLUtils
/**
  * Created by waixingren on 21/01/2018.
  */
object Classification {

  def main(args: Array[String]): Unit = {


    val sc = SparkUtil.getSparkContext("local")

    // Load and parse the data file.
    val data = MLUtils.loadLibSVMFile(sc, "file:///Users/waixingren/bigdata-java/spark/sparkproj/data/mllib/sample_libsvm_data_small.txt")
//    data.collect.foreach(println)
    // Split the data into training and test sets (30% held out for testing)
    val splits = data.randomSplit(Array(0.7, 0.3))
//    val splits2 = data.randomSplit(Array(1.0))
//    splits2(0).collect.foreach(println)
    val (trainingData, testData) = (splits(0), splits(1))
//    trainingData.collect.foreach(println)
    // Train a DecisionTree model.
    //  Empty categoricalFeaturesInfo indicates all features are continuous.
    val numClasses = 2
    val categoricalFeaturesInfo = Map[Int, Int]()
    val impurity = "gini"
    val maxDepth = 5
    val maxBins = 32

    val model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
      impurity, maxDepth, maxBins)

    // Evaluate model on test instances and compute test error
    val labelAndPreds = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
//    labelAndPreds.collect.foreach(println)

//    val testErr = labelAndPreds.filter(r => r._1 != r._2).count().toDouble / testData.count()
//    println("Test Error = " + testErr)
//    println("Learned classification tree model:\n" + model.toDebugString)

    // Save and load model
    model.save(sc, "target/tmp/myDecisionTreeClassificationModel")
    val sameModel = DecisionTreeModel.load(sc, "target/tmp/myDecisionTreeClassificationModel")
  }

}
