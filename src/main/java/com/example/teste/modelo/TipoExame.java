package com.example.teste.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name = "tipo_exame")
@Data
public class TipoExame extends AbstractEntity{
	private String descricao;
}
