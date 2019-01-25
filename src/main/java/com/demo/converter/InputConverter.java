package com.demo.converter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.entity.InputEntity;
import com.demo.model.InputModel;
import com.demo.util.Util;

@Component
public class InputConverter {
	
	@Autowired
	ResultadoConverter resultadoConverter;
	
	public InputEntity convertModel2Entity(InputModel model) throws ParseException {
		InputEntity entity = new InputEntity();
		entity.setId(model.getId());
		entity.setTipoRecurso(model.getTipoRecurso());
		entity.setFechaIngreso(Util.fromStringToDate(model.getFechaIngreso()));
		entity.setFechaResolucion(Util.fromStringToDate(model.getFechaResolucion()));
		entity.setEmpresa(model.getEmpresa());
		entity.setDistrito(model.getDistrito());
		entity.setMateria(model.getMateria());
		entity.setSubmateria(model.getSubmateria());
		entity.setServicio(model.getServicio());
		
		if (model.getSentido() != null)
			entity.setSentido(resultadoConverter.convertModel2Entity(model.getSentido()));
		entity.setEsCorrecto(model.getEsCorrecto());
//		if (model.getResultadoEsperado() != null)
//			entity.setResultadoEsperado(resultadoConverter.convertModel2Entity(model.getResultadoEsperado()));
		return entity;
	}
	
	public InputModel convertEntity2Model(InputEntity entity) throws ParseException {
		InputModel model = new InputModel();
		model.setId(entity.getId());
		model.setTipoRecurso(entity.getTipoRecurso());
		model.setFechaIngreso(Util.fromDateToString(entity.getFechaIngreso()));
		model.setFechaResolucion(Util.fromDateToString(entity.getFechaResolucion()));
		model.setEmpresa(entity.getEmpresa());
		model.setDistrito(entity.getDistrito());
		model.setMateria(entity.getMateria());
		model.setSubmateria(entity.getSubmateria());
		model.setServicio(entity.getServicio());
		
		if (entity.getSentido() != null)
			model.setSentido(resultadoConverter.convertEntity2Model(entity.getSentido()));
		model.setEsCorrecto(entity.getEsCorrecto());
//		if (entity.getResultadoEsperado() != null)
//			model.setResultadoEsperado(resultadoConverter.convertEntity2Model(entity.getResultadoEsperado()));
		return model;
	}
	
	public List<InputEntity> convertListModel2Entity(List<InputModel> listaModel) throws ParseException {
		List<InputEntity> listaEntity = new ArrayList<>();
		for (InputModel model : listaModel) {
			listaEntity.add(convertModel2Entity(model));
		}
		return listaEntity;
	}
	
	public List<InputModel> convertListEntity2Model(List<InputEntity> listaEntity) throws ParseException {
		List<InputModel> listaModel = new ArrayList<>();
		for (InputEntity entity : listaEntity) {
			listaModel.add(convertEntity2Model(entity));
		}
		return listaModel;
	}
}
