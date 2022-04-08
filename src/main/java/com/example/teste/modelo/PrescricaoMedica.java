package com.example.teste.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;


@SuppressWarnings("serial")
@Entity
@Table(name = "prescricao_medica")
@Data
public class PrescricaoMedica extends AbstractEntity{
	 
	private static final long serialVersionUID = -6217019432752038720L;
	
	private Paciente paciente;
	private Medico medico;
	private String descricao;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_registro", nullable = false)
	private LocalDate dataRegistro;

}
