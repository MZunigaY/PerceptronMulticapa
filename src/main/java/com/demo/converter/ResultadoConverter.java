package com.demo.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.entity.ResultadoEntity;
import com.demo.model.ResultadoModel;

@Component
public class ResultadoConverter {
	
	public ResultadoEntity convertModel2Entity(ResultadoModel model) {
		ResultadoEntity entity = new ResultadoEntity();
		entity.setId(model.getId());
		entity.setValorResultado(model.getValor());
		return entity;
	}
	
	public ResultadoModel convertEntity2Model(ResultadoEntity entity) {
		ResultadoModel model = new ResultadoModel();
		model.setId(entity.getId());
		model.setValor(entity.getValorResultado());
		return model;
	}
	
	public List<ResultadoEntity> convertListModel2Entity(List<ResultadoModel> listaModel) {
		List<ResultadoEntity> listaEntity = new ArrayList<>();
		for (ResultadoModel model : listaModel) {
			listaEntity.add(convertModel2Entity(model));
		}
		return listaEntity;
	}
	
	public List<ResultadoModel> convertListEntity2Model(List<ResultadoEntity> listaEntity) {
		List<ResultadoModel> listaModel = new ArrayList<>();
		for (ResultadoEntity entity : listaEntity) {
			listaModel.add(convertEntity2Model(entity));
		}
		return listaModel;
	}

}
