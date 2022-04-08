package com.example.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.teste.modelo.Funcionario;
import com.example.teste.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	@Transactional(readOnly = true)
	public Funcionario buscarPorUsuarioEmail(String email) {
		
		return repository.findByUsuarioEmail(email).orElse(new Funcionario());
	}

	@Transactional(readOnly = false)
	public void salvar(Funcionario funcionario) {
		repository.save(funcionario);		
	}

	@Transactional(readOnly = false)
	public void editar(Funcionario funcionario) {
		Funcionario f2 = repository.findById(funcionario.getId()).get();
		f2.setNome(funcionario.getNome());
		f2.setDataNascimento(funcionario.getDataNascimento());
		f2.setTelefone(funcionario.getTelefone());
		f2.setGenero(funcionario.getGenero());
	}
}
