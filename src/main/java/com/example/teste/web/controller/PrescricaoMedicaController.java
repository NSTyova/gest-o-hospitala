package com.example.teste.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.teste.modelo.PrescricaoMedica;
import com.example.teste.service.MedicoService;
import com.example.teste.service.PrescricaoMedicaService;

@Controller
@RequestMapping("prescricao")
public class PrescricaoMedicaController {

	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private PrescricaoMedicaService service;
	
	// abre a pagina de prescricao 
		@GetMapping({"/prescicaoM"})
		public String agendarConsulta(PrescricaoMedica prescricaoMedica) {

			return "consulta/prescricao";		
		}
	
}
