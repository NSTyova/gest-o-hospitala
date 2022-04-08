package com.example.teste.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "pacientes")
@Data
public class Paciente extends AbstractEntity {

	@Column(name = "nome", unique = true, nullable = false)
	private String nome;

	@Column(name = "data_nascimento", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dtNascimento;
	@Column(name = "numero_telefone", unique = true, length = 15)
	private String telefone;

	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Agendamento> agendamentos;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	
}
