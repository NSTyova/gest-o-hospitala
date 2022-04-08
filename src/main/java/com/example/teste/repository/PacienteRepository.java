package com.example.teste.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.teste.modelo.Especialidade;
import com.example.teste.modelo.Paciente;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query("select p from Paciente p where p.usuario.email like :email")
	Optional<Paciente> findByUsuarioEmail(String email);
	
	@Query("select p.nome from Paciente p where p.nome like :termo%")
	List<String> findPacientesByTermo(String termo);

	@Query("select p from Paciente p where p.nome IN :nomes")
	Set<Paciente> findByTitulos(String[] nomes);
	
	@Query("select e from Paciente e where e.nome like :search%")
	Page<Especialidade> findAllByNome(String search, Pageable pageable);
}
