package com.demo.entrenamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.ArrayUtils;

import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ModelClassifier {

	private Attribute tipRecurso;
	private Attribute fechaIngreso_2InstDay;
	private Attribute fechaIngreso_2InstMonth;
	private Attribute fechaIngreso_2InstYear;
	private Attribute fechaResolTRASU_Day;
	private Attribute fechaResolTRASU_Month;
	private Attribute fechaResolTRASU_Year;
	private Attribute empresa;
	private Attribute distrito;
	private Attribute materia;
	private Attribute submateria;
	private Attribute servicio;
	// Clase
	private ArrayList<String> sentido;

	private ArrayList<Attribute> attributes;
	private Instances dataRaw;

	public ModelClassifier() {
		tipRecurso = new Attribute("TipRecurso");
		fechaIngreso_2InstDay = new Attribute("FechaIngreso_2InstDay");
		fechaIngreso_2InstMonth = new Attribute("FechaIngreso_2InstMonth");
		fechaIngreso_2InstYear = new Attribute("FechaIngreso_2InstYear");
		fechaResolTRASU_Day = new Attribute("FechaResolTRASU_Day");
		fechaResolTRASU_Month = new Attribute("FechaResolTRASU_Month");
		fechaResolTRASU_Year = new Attribute("FechaResolTRASU_Year");
		empresa = new Attribute("Empresa");
		distrito = new Attribute("Distrito");
		materia = new Attribute("Materia");
		submateria = new Attribute("Submateria");
		servicio = new Attribute("Servicio");

		attributes = new ArrayList<Attribute>();
		sentido = new ArrayList<String>();
		sentido.add("1");
		sentido.add("2");
		sentido.add("3");
		sentido.add("4");

		attributes.add(tipRecurso);
		attributes.add(fechaIngreso_2InstDay);
		attributes.add(fechaIngreso_2InstMonth);
		attributes.add(fechaIngreso_2InstYear);
		attributes.add(fechaResolTRASU_Day);
		attributes.add(fechaResolTRASU_Month);
		attributes.add(fechaResolTRASU_Year);
		attributes.add(empresa);
		attributes.add(distrito);
		attributes.add(materia);
		attributes.add(submateria);
		attributes.add(servicio);

		attributes.add(new Attribute("Sentido", sentido));
		dataRaw = new Instances("TestInstances", attributes, 0);
		dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
	}

	// public Instances createInstance(double petallength, double petalwidth,
	// double result) {
	// dataRaw.clear();
	// double[] instanceValue1 = new double[]{petallength, petalwidth, 0};
	// dataRaw.add(new DenseInstance(1.0, instanceValue1));
	// return dataRaw;
	// }

//	public Instances createInstance(List<Double> inputs) {
//		dataRaw.clear();
//		inputs.add(0.0);
//		Double[] instanceValue1 = new Double[inputs.size()];
//		instanceValue1 = inputs.toArray(instanceValue1);
//		dataRaw.add(new DenseInstance(1.0, ArrayUtils.toPrimitive(instanceValue1)));
//		return dataRaw;
//	}

	public Instances createInstance(double[] inputs) {
		dataRaw.clear();
		dataRaw.add(new DenseInstance(1.0, inputs));
		return dataRaw;
	}

	public String classifiy(Instances insts, String path) {
		String result = "Not classified!!";
		Classifier cls = null;
		try {
			cls = (MultilayerPerceptron) SerializationHelper.read(path);
			result = sentido.get((int) cls.classifyInstance(insts.firstInstance()));
		} catch (Exception ex) {
			Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public Instances getInstance() {
		return dataRaw;
	}

}
