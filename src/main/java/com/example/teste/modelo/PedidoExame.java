package com.example.teste.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name = "pedido_exame")
@Data
public class PedidoExame extends AbstractEntity{

	@Column(name = "dataPedido", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataRegistro=LocalDate.now();
	@ManyToOne
	@JoinColumn(name = "medico_Pediu")
	private Medico medico;
	@ManyToOne
	@JoinColumn(name = "tipo_Exame")
	private TipoExame tipoExame;
	private BigDecimal valor;
	@ManyToOne
	@JoinColumn(name = "paciente")
	private Paciente paciente;
	
	@Column(name = "resultado_exame", length = 200)
	private String resultado;
	
}
