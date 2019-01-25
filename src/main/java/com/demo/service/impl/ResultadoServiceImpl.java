package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.converter.ResultadoConverter;
import com.demo.model.ResultadoModel;
import com.demo.repository.ResultadoRepository;
import com.demo.service.ResultadoService;

@Service
public class ResultadoServiceImpl implements ResultadoService {
	
	@Autowired
	ResultadoConverter resultadoConverter;
	
	@Autowired
	ResultadoRepository resultadoRepository;

	@Override
	public List<ResultadoModel> obtenerResultadosEsperados() {
		return resultadoConverter.convertListEntity2Model(resultadoRepository.findAll());
	}

}
