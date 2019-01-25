package com.demo.entrenamiento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.util.Util;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

@Component
public class Entrenamiento {

	public static final String datasetPath = "C:/Users/ASUS/Documents/wk_REDES_NEURONALES/SGRE/src/main/resources/static/data/dataset_5clases.arff";
	public static final String modelPath = "C:/Users/ASUS/Documents/wk_REDES_NEURONALES/SGRE/src/main/resources/static/data/model_sgre.bin";

	public double realizarEntrenamiento() throws Exception {
		ModelGenerator mg = new ModelGenerator();
		Instances dataset = mg.loadDataset(datasetPath);

		Filter filter = new Normalize();
		dataset.randomize(new Debug.Random(1));

		// dividir el dataset para entrenar el 80% y testear el 20%
		int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
		int testSize = dataset.numInstances() - trainSize;

		// normalizar data
		filter.setInputFormat(dataset);
		Instances datasetnor = Filter.useFilter(dataset, filter);

		Instances traindataset = new Instances(datasetnor, 0, trainSize);
		Instances testdataset = new Instances(datasetnor, trainSize, testSize);

		// entrenar con el dataset
		MultilayerPerceptron ann = (MultilayerPerceptron) mg.buildClassifier(traindataset);

		// guardar el modelo generado
		mg.saveModel(ann, modelPath);

		// porcentaje de instancias clasificadas correctamente
		System.out.println("################TERMINO EL ENTRENAMIENTO");
		return Util.redondear(mg.evaluateModel(ann, traindataset, testdataset), 2);
	}

	@SuppressWarnings("unused")
	public String pruebaInstancia(List<Double> inputs) throws Exception {
		ModelGenerator mg = new ModelGenerator();
		Instances dataset = mg.loadDataset(datasetPath);
		Filter filter = new Normalize();
		dataset.randomize(new Debug.Random(1));

		int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
		int testSize = dataset.numInstances() - trainSize;

		// normalizar data
		filter.setInputFormat(dataset);
		Instances datasetnor = Filter.useFilter(dataset, filter);
		Instances testdataset = new Instances(datasetnor, trainSize, testSize);

		ModelClassifier cls = new ModelClassifier();

		// agregar la salida : cero
		inputs.add(new Double(0));

		// classname
		return cls.classifiy(Filter.useFilter(cls.createInstance(Util.convertirAPrimitivo(inputs)), filter), modelPath);
	}

	public void aprendizaje(List<Double> inputs) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String data = Util.convertirListaAString(inputs);

			File file = new File(datasetPath);

			// crear archivo en caso no exista
			// if (!file.exists()) {
			// file.createNewFile();
			// }

			// se hace append al input
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
