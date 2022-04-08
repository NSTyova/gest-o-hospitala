package com.example.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.modelo.PrescricaoMedica;

@Repository
public interface PrescricaoMedicaRepository extends JpaRepository<PrescricaoMedica, Long>{

}
