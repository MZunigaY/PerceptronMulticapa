package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ENTRENAMIENTO_PESOS", schema = "redes_neuronales")
@Getter
@Setter
public class PesoEntity {

	@Id
	@Column(name = "ID_PESO")
	private int id;

	@Column(name = "VALOR_PESO")
	private String valorPeso;

}
