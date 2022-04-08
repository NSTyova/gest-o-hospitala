package com.example.teste.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.teste.datatables.Datatables;
import com.example.teste.datatables.DatatablesColunas;
import com.example.teste.modelo.Especialidade;
import com.example.teste.modelo.Paciente;
import com.example.teste.repository.PacienteRepository;



@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	@Autowired
	private Datatables datatables;
	
	@Transactional(readOnly = true)
	public Paciente buscarPorUsuarioEmail(String email) {
		
		return repository.findByUsuarioEmail(email).orElse(new Paciente());
	}

	@Transactional(readOnly = false)
	public void salvar(Paciente paciente) {
		
		repository.save(paciente);		
	}

	@Transactional(readOnly = false)
	public void editar(Paciente paciente) {
		Paciente p2 = repository.findById(paciente.getId()).get();
		p2.setNome(paciente.getNome());
		p2.setTelefone(paciente.getTelefone());
		p2.setDtNascimento(paciente.getDtNascimento());		
	}
	
	@Transactional(readOnly = true)
	public List<String> buscarPacientesByTermo(String termo) {
		
		return repository.findPacientesByTermo(termo);
	}

	@Transactional(readOnly = true)
	public Set<Paciente> buscarPorTitulos(String[] titulos) {
		
		return repository.findByTitulos(titulos);
	}
	
	@Transactional(readOnly = true)
	public Paciente buscarPorId(Long id) {
		return repository.findById(id).get();
	}


	@Transactional(readOnly = true)
	public Map<String, Object> buscarPacientes(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.PACIENTES);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())
				: repository.findAllByNome(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}
}
