package com.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputModel {
	
	private int id;
	private String tipoRecurso;
	private String fechaIngreso;
	private String fechaResolucion;
	private String empresa;
	private String distrito;
	private String materia;
	private String submateria;
	private String servicio;
	private ResultadoModel sentido;
	private int esCorrecto;
//	private ResultadoModel resultadoEsperado;

}
