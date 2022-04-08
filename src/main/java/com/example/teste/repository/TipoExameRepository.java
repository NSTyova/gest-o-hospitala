package com.example.teste.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.teste.modelo.Especialidade;
import com.example.teste.modelo.Paciente;
import com.example.teste.modelo.TipoExame;

@Repository
public interface TipoExameRepository extends JpaRepository<TipoExame, Long>{
	
	// pegar os tipos de exames
	@Query("select p.descricao from TipoExame p where p.descricao like :termo%")
	List<String> findPacientesByTermo(String termo);

	@Query("select p from TipoExame p where p.descricao IN :nomes")
	Set<TipoExame> findByDescricao(String[] nomes);

	@Query("select e from TipoExame e where e.descricao like :search%")
	Page<TipoExame> findAllByDescricao(String search, Pageable pageable);
	
	
}
	

