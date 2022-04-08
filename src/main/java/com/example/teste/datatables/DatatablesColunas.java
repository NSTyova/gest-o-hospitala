package com.example.teste.datatables;

public class DatatablesColunas {

	public static final String[] ESPECIALIDADES = {"id", "titulo"};

	public static final String[] MEDICOS = {"id", "nome", "crm", "dtInscricao", "especialidades"};
	
	public static final String[] AGENDAMENTOS = {"id", "paciente.nome", "dataConsulta", "medico.nome", "especialidade.titulo"};
	
	public static final String[] CONSULTAS = {"id",  "dataConsulta", "medico.nome", "especialidade.titulo"};

	public static final String[] USUARIOS = {"id", "email", "ativo", "perfis"};	
	
	public static final String[] PACIENTES = {"id", "nome", "dtNascimento", "telefone"};	 
	
	public static final String[] CLIENTES = {"id", "nome", "telefone", "sexo"};
	
	public static final String[] FORNECEDORES = {"id", "nome", "telefone", "sexo"};
	
	public static final String[] PRODUTOS = {"id", "nome", "peso", "laboratorio", "origem.descricao", "fornecedores.nome"};
	
	public static final String[] TIPOEXAME = {"id","descricao"};
	
	public static final String[] ESTOQUE = {"id","quantidade", "produto.nome"};
	
	public static final String[] PEDIDOEXAMES = {"id", "medico.nome", "tipoExame.descricao", "paciente.nome", "dataRegistro", "paciente.telefone"};
}
