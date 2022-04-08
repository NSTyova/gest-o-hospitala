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
import com.example.teste.modelo.TipoExame;
import com.example.teste.repository.TipoExameRepository;

@Service
public class TipoExameService {

	
	@Autowired
	private TipoExameRepository repository;
	@Autowired
	private Datatables datatables;
	

	@Transactional(readOnly = false)
	public void salvar(TipoExame tipoExame) {
		repository.save(tipoExame);
	}

	

	
	
	@Transactional(readOnly = true)
	public Map<String, Object> buscarTipoExames(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.TIPOEXAME);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable())
				: repository.findAllByDescricao(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}



	@Transactional(readOnly = true)
	public List<String> buscarTipoExamesByTermo(String termo) {
		return repository.findPacientesByTermo(termo);
	}

	@Transactional(readOnly = true)
	public Set<TipoExame> buscarPorTitulos(String[] titulos) {
		return repository.findByDescricao(titulos);
	}
	
	@Transactional(readOnly = true)
	public TipoExame buscarPorId(Long id) {
		return repository.findById(id).get();
	}
	
	
}
