package com.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RESULTADOS_PRUEBAS", schema = "redes_neuronales")
@Getter
@Setter
public class ResultadoEntity {
	
	@Id
	@Column(name = "ID_RESULTADO")
	private int id;
	
	@Column(name = "VALOR_RESULTADO")
	private String valorResultado;
	
	@OneToMany(mappedBy = "sentido")
	private List<InputEntity> inputEntityResultado;
	
//	@OneToMany(mappedBy = "resultadoEsperado")
//	private List<InputEntity> inputEntityResultadoEsperado;
	
	
}
