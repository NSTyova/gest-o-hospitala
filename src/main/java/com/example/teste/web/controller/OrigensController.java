package com.example.teste.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teste.service.OrigensService;

@Controller
@RequestMapping("origens")
public class OrigensController {
	
	
	@Autowired
	private OrigensService service;

	@GetMapping("/titulos")
	public ResponseEntity<?> getOrigensrPorTermo(@RequestParam("termo") String termo) {
		List<String> origens = service.buscarOrigensByTermo(termo); 
		return ResponseEntity.ok(origens);
	}
}
