/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

/**
 *
 * @author Farshid rayhan
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.trees.J48;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class Weka {

    /**
     * @param args the command line arguments
     */
    public void naiveBayes(String path) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            Instances train = new Instances(reader);
            train.setClassIndex(train.numAttributes() - 1);      // for 0 indexing
            reader.close();

            Classifier naiveBayes = new NaiveBayes();
            naiveBayes.buildClassifier(train);

            Evaluation evaluation = new Evaluation(train);

            evaluation.crossValidateModel(naiveBayes, train, 10, new Random(1));

            System.out.println(evaluation.toSummaryString("\nResults...\n", true));
            System.out.println(evaluation.fMeasure(1) + " " + evaluation.precision(1) + " " + evaluation.recall(1));

            //BufferedReader testReader = new BufferedReader(new FileReader("C:\\Users\\Farshid rayhan\\Desktop\\test.arff"));
            
            path = "C:\\Users\\\\Farshid rayhan\\\\Desktop\\\\test.arff";
            
            Instances isTrainingSet = new Instances(new BufferedReader(new FileReader(path)));
            
            Evaluation eval = new Evaluation(isTrainingSet);
//            eval.
            
//                     double[] fDistribution = naiveBayes.distributionForInstance(isTrainingSet);

        } catch (Exception ex) {
             
        }
    }

    public static void main(String[] args) {
        Weka w = new Weka();
//        System.out.println(" ### Nominal.........");
//       w.naiveBayes("C:\\Users\\Farshid rayhan\\Downloads\\weather.nominal.arff");

        System.out.println(" ### Non nominal.........");
        w.naiveBayes("C:\\Users\\Farshid rayhan\\Downloads\\weather.arff");

    }

}
