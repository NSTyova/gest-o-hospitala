package com.example.teste.repository.projection;

import com.example.teste.modelo.Especialidade;
import com.example.teste.modelo.Funcionario;
import com.example.teste.modelo.Medico;
import com.example.teste.modelo.Paciente;

public interface HistoricoFuncionario {

	Long getId();

	Paciente getPaciente();

	String getDataConsulta();

	Medico getMedico();

	Especialidade getEspecialidade();
	
	Funcionario getFuncionario();
}
