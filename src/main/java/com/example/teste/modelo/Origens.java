package com.example.teste.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name = "origens")
@Data
public class Origens  extends AbstractEntity{

	
	private String descricao;
	private String abreviatura;
	
	
	
}
