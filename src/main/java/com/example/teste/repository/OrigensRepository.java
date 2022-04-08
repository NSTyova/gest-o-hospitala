package com.example.teste.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.teste.modelo.Origens;

@Repository
public interface OrigensRepository  extends JpaRepository<Origens, Long>{

	@Query("select o from Origens o where o.descricao IN :titulos")
	Set<Origens> findByDescricao(String[] titulos);
	
	@Query("select o.descricao from Origens o where o.descricao like :termo%")
	List<String> findOrigensByTermo(String termo);
}
