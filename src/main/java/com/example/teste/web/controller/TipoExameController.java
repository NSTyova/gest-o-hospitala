package com.example.teste.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.teste.modelo.TipoExame;
import com.example.teste.service.TipoExameService;


@Controller
@RequestMapping("tipoExames")
public class TipoExameController {

	@Autowired
	private TipoExameService service;

	@GetMapping("/gravar")
	public String abrir(TipoExame tipoExame) {

		return "tipoExame/tipoExame";
	}
	
	@PostMapping("/salvar")
	public String salvar(TipoExame tipoExame, RedirectAttributes attr) {
		service.salvar(tipoExame);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		return "redirect:/tipoExames/gravar";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getTipoExames(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarTipoExames(request));
	}
	
	@GetMapping("/descricoes")
	public ResponseEntity<?> getTipoExamesPorTermo(@RequestParam("termo") String termo) {
		List<String> tipoExames = service.buscarTipoExamesByTermo(termo);
		System.out.println(">>>>>>>>>>>>>>>>>>> aqui chegou<<<<<<<<<<<<<"); 
		return ResponseEntity.ok(tipoExames);
	}
		
}
