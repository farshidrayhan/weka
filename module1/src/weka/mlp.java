/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

/**
 *
 * @author Farshid rayhan
 */
public class mlp {

    public static void main(String[] args ){
        try {
//Reading training arff or csv file
            FileReader trainreader = new FileReader("C:/Users/Farshid rayhan/Desktop/2.arff");
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);

//Instance of NN
            MultilayerPerceptron mlp = new MultilayerPerceptron();
//Setting Parameters
            mlp.setLearningRate(0.1);
            mlp.setMomentum(0.2);
            mlp.setTrainingTime(2000);
            mlp.setHiddenLayers("3");

            mlp.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(mlp, train);
            System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
            System.out.println(eval.toSummaryString()); //Summary of Training

            Instances datapredict = new Instances(new BufferedReader(new FileReader("C:/Users/Farshid rayhan/Desktop/1.arff")));
            datapredict.setClassIndex(datapredict.numAttributes() - 1);
            Instances predicteddata = new Instances(datapredict);
//Predict Part
            for (int i = 0; i < datapredict.numInstances(); i++) {
                double clsLabel = mlp.classifyInstance(datapredict.instance(i));
                predicteddata.instance(i).setClassValue(clsLabel);
            }
//Storing again in arff
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Farshid rayhan/Desktop/ann.arff"));
            writer.write(predicteddata.toString());
            writer.newLine();
            writer.flush();
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

}
