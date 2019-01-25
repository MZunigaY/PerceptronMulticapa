package com.demo.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.converter.InputConverter;
import com.demo.entrenamiento.Entrenamiento;
import com.demo.model.InputModel;
import com.demo.model.ResultadoModel;
import com.demo.repository.InputRepository;
import com.demo.service.InputService;

@Service
public class InputServiceImpl implements InputService {

	@Autowired
	InputRepository inputRepository;

	@Autowired
	InputConverter inputConverter;

	@Autowired
	Entrenamiento entrenamiento;

	@Override
	public List<InputModel> obtenerListaPrueba() throws ParseException {
		return inputConverter.convertListEntity2Model(inputRepository.findAll());
	}

	@Override
	public InputModel agregarInputPrueba(InputModel inputModel) throws Exception {
		String diaFI = inputModel.getFechaIngreso().split("/")[0];
		String mesFI = inputModel.getFechaIngreso().split("/")[1];
		String anioFI = inputModel.getFechaIngreso().split("/")[2];

		String diaFR = inputModel.getFechaResolucion().split("/")[0];
		String mesFR = inputModel.getFechaResolucion().split("/")[1];
		String anioFR = inputModel.getFechaResolucion().split("/")[2];

		List<Double> inputs = new ArrayList<>();
		inputs.add(Double.valueOf(inputModel.getTipoRecurso()));
		inputs.add(Double.valueOf(diaFI));
		inputs.add(Double.valueOf(mesFI));
		inputs.add(Double.valueOf(anioFI));
		inputs.add(Double.valueOf(diaFR));
		inputs.add(Double.valueOf(mesFR));
		inputs.add(Double.valueOf(anioFR));
		inputs.add(Double.valueOf(inputModel.getEmpresa()));
		inputs.add(Double.valueOf(inputModel.getDistrito()));
		inputs.add(Double.valueOf(inputModel.getMateria()));
		inputs.add(Double.valueOf(inputModel.getSubmateria()));
		inputs.add(Double.valueOf(inputModel.getServicio()));
		
		List<Double> listaAprendizaje = new ArrayList<>(inputs);
		// entrenamiento.realizarEntrenamiento();
		ResultadoModel resultado = new ResultadoModel();
		String respuesta = entrenamiento.pruebaInstancia(inputs);
		resultado.setId(Integer.valueOf(respuesta));
		System.out.println("##################      ->  " + resultado.getId());

		inputModel.setSentido(resultado);

		// se debe agregar el input al archivo de entrenamiento
		listaAprendizaje.add(Double.valueOf(respuesta));
		entrenamiento.aprendizaje(listaAprendizaje);

		return inputConverter.convertEntity2Model(inputRepository.save(inputConverter.convertModel2Entity(inputModel)));
	}

	@Override
	public void borrarListaPruebas() {
		inputRepository.deleteAll();
	}

	@Override
	public InputModel findInputByIdModel(int id) throws ParseException {
		return inputConverter.convertEntity2Model(inputRepository.findById(id).get());
	}

}
