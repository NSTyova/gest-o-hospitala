package com.example.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.teste.modelo.PrescricaoMedica;
import com.example.teste.repository.PrescricaoMedicaRepository;
@Service
public class PrescricaoMedicaService {
	
	
	@Autowired
	private PrescricaoMedicaRepository repository;

	@Transactional(readOnly = false)
	public void salvar(PrescricaoMedica prescricaoMedica) {
		repository.save(prescricaoMedica);
	}
}
