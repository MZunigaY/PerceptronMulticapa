package com.demo.service;

import java.text.ParseException;
import java.util.List;

import com.demo.model.InputModel;

public interface InputService {
	
	List<InputModel> obtenerListaPrueba() throws ParseException;
	InputModel agregarInputPrueba(InputModel inputModel) throws Exception;
	void borrarListaPruebas();
	InputModel findInputByIdModel(int id) throws ParseException;

}
