package com.example.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.teste.modelo.Funcionario;

@Repository
public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long>{

	@Query("select f from Funcionario f where f.usuario.email like :email")
	Optional<Funcionario> findByUsuarioEmail(String email);

}
