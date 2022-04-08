package com.example.teste.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.teste.modelo.Origens;
import com.example.teste.repository.OrigensRepository;

@Service
public class OrigensService {

	@Autowired
	private OrigensRepository repository;

	@Transactional(readOnly = false)
	public void salvar(Origens origens) {
		
		repository.save(origens);
	}

	
	@Transactional(readOnly = true)
	public Origens buscarPorId(Long id) {
		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		
		repository.deleteById(id);
	}

	

	@Transactional(readOnly = true)
	public Set<Origens> buscarPorDescricao(String[] titulos) {
		
		return repository.findByDescricao(titulos);
	}

	
	@Transactional(readOnly = true)
	public List<String> buscarOrigensByTermo(String termo) {
		
		return repository.findOrigensByTermo(termo);
	}

}
