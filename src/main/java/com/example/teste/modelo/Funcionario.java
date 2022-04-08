package com.example.teste.modelo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "funcionarios")
public class Funcionario extends AbstractEntity implements Serializable{

	@Column(name = "nome_funcionario", nullable = false)
	private String nome;
	@Column(name = "telefone_funcionario", nullable = false)
	private String telefone;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_inscricao", nullable = false)
	private LocalDate dataNascimento;
	private String genero;
	@Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean ativo;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public Funcionario() {
		super();
	}

	public Funcionario(Long id) {
		super.setId(id);
	}

	public Funcionario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
