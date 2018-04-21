package com.tencent.cubeli.sparkmllib.nativebayes
import com.tencent.cubeli.common.SparkUtil
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
/**
  * Created by waixingren on 24/01/2018.
  */
object NativeBayes {

  def main(args: Array[String]): Unit = {

    val sc = SparkUtil.getSparkContext("local")
    val data = MLUtils.loadLibSVMFile(sc, "file:///Users/liyong/software/sparktraining/data/mllib/sample_libsvm_data.txt")

    // Split data into training (60%) and test (40%).
    val Array(training, test) = data.randomSplit(Array(0.6, 0.4))

    val model = NaiveBayes.train(training, lambda = 1.0, modelType = "multinomial")

    val predictionAndLabel = test.map(p => (model.predict(p.features), p.label))
    val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()

    // Save and load model
    model.save(sc, "target/tmp/myNaiveBayesModel")
    val sameModel = NaiveBayesModel.load(sc, "target/tmp/myNaiveBayesModel")

  }

}
