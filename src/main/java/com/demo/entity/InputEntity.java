package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRUEBA_INPUT", schema = "REDES_NEURONALES")
@Getter
@Setter
public class InputEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SQ_PRUEBA_INPUT_PK", sequenceName = "REDES_NEURONALES.SQ_PRUEBA_INPUT_PK", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRUEBA_INPUT_PK")
	@Column(name = "ID_INPUT")
	private int id;
	
	@Column(name = "TIPO_RECURSO")
	private String tipoRecurso;
	
	@Column(name = "FEC_INGRESO")
	private Date fechaIngreso;
	
	@Column(name = "FEC_RESOL")
	private Date fechaResolucion;
	
	@Column(name = "EMPRESA")
	private String empresa;
	
	@Column(name = "DISTRITO")
	private String distrito;
	
	@Column(name = "MATERIA")
	private String materia;
	
	@Column(name = "SUBMATERIA")
	private String submateria;
	
	@Column(name = "SERVICIO")
	private String servicio;
	
	@ManyToOne
	@JoinColumn(name = "SENTIDO")
	private ResultadoEntity sentido;
	
	@Column(name = "ES_CORRECTO")
	private int esCorrecto;
		
	
}
