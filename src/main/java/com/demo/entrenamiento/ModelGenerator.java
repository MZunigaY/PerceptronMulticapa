package com.demo.entrenamiento;

import java.util.logging.Level;
import java.util.logging.Logger;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Taha Emara Website: http://www.emaraic.com Email : taha@emaraic.com
 *         Created on: Jun 28, 2017 Github link:
 *         https://github.com/emara-geek/weka-example
 */
public class ModelGenerator {

	public Instances loadDataset(String path) {
		Instances dataset = null;
		try {
			dataset = DataSource.read(path);
			if (dataset.classIndex() == -1) {
				dataset.setClassIndex(dataset.numAttributes() - 1);
			}
		} catch (Exception ex) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}

		return dataset;
	}

	public Classifier buildClassifier(Instances traindataset) {
		MultilayerPerceptron m = new MultilayerPerceptron();

		m.setGUI(false);
		m.setAutoBuild(true);
		m.setBatchSize("100");
		m.setDebug(false);
		m.setDecay(false);
		m.setDoNotCheckCapabilities(false);
		m.setHiddenLayers("a");
		m.setLearningRate(0.2);
		m.setMomentum(0.1);
		m.setNominalToBinaryFilter(true);
		m.setNormalizeAttributes(true);
		m.setNormalizeNumericClass(true);
		m.setNumDecimalPlaces(2);
		m.setReset(true);
		m.setSeed(0);
		m.setTrainingTime(500);// epochs
		m.setValidationSetSize(0);
		m.setValidationThreshold(20);

		try {
			m.buildClassifier(traindataset);

		} catch (Exception ex) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}
		return m;
	}

	public double evaluateModel(Classifier model, Instances traindataset, Instances testdataset) {
		Evaluation eval = null;
		try {
			// se evalua el modelo con la data de test
			eval = new Evaluation(traindataset);
			eval.evaluateModel(model, testdataset);
		} catch (Exception ex) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}
		// porcentaje de instancias clasificadas correctamente
		return eval.pctCorrect();
	}

	public void saveModel(Classifier model, String modelpath) {
		try {
			SerializationHelper.write(modelpath, model);
		} catch (Exception ex) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
