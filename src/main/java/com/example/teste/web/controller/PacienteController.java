package com.example.teste.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teste.modelo.Paciente;
import com.example.teste.modelo.Usuario;
import com.example.teste.service.PacienteService;
import com.example.teste.service.UsuarioService;



@Controller
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteService service;
	@Autowired
	private UsuarioService usuarioService;
	
	
	// abre a pagina de agendamento de consultas 
		@GetMapping({"/gravarPaciente"})
		public String agendarConsulta(Paciente paciente) {

			return "consulta/cadastroPaciente";		
		}
	
	// abrir pagina de dados pessoais do paciente
	@GetMapping("/dados")
	public String cadastrar(Paciente paciente, ModelMap model, @AuthenticationPrincipal User user) {
		paciente = service.buscarPorUsuarioEmail(user.getUsername());
		if (paciente.hasNotId()) {
			paciente.setUsuario(new Usuario(user.getUsername()));
		}
		model.addAttribute("paciente", paciente);
		return "paciente/cadastro";
	}
	
	//salvar os dados do paciente pelo funcionario
	@PostMapping("/salvarF")
	public String salvar(Paciente paciente, ModelMap model) {
		service.salvar(paciente);
		model.addAttribute("sucesso", "Seus dados foram inseridos com sucesso.");
		return "consulta/cadastroPaciente";	
	}
	
	// salvar o form de dados pessoais do paciente com verificacao de senha
	@PostMapping("/salvar")
	public String salvar(Paciente paciente, ModelMap model, @AuthenticationPrincipal User user) {
		Usuario u = usuarioService.buscarPorEmail(user.getUsername());
		if (UsuarioService.isSenhaCorreta(paciente.getUsuario().getSenha(), u.getSenha())) {
			paciente.setUsuario(u);
			service.salvar(paciente);
			model.addAttribute("sucesso", "Seus dados foram inseridos com sucesso.");
		} else {
			model.addAttribute("falha", "Sua senha não confere, tente novamente.");
		}
		return "paciente/cadastro";
	}	
	
	// editar o form de dados pessoais do paciente com verificacao de senha
	@PostMapping("/editar")
	public String editar(Paciente paciente, ModelMap model, @AuthenticationPrincipal User user) {
		Usuario u = usuarioService.buscarPorEmail(user.getUsername());
		if (UsuarioService.isSenhaCorreta(paciente.getUsuario().getSenha(), u.getSenha())) {
			service.editar(paciente);
			model.addAttribute("sucesso", "Seus dados foram editados com sucesso.");
		} else {
			model.addAttribute("falha", "Sua senha não confere, tente novamente.");
		}
		return "paciente/cadastro";
	}	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("paciente", service.buscarPorId(id));
		return "consulta/cadastroPaciente";	
	}
	
	@GetMapping("/titulos")
	public ResponseEntity<?> getPacientesPorTermo(@RequestParam("termo") String termo) {
		List<String> pacientes = service.buscarPacientesByTermo(termo);
		return ResponseEntity.ok(pacientes);
	}
		
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getEspecialidades(HttpServletRequest request) {

		return ResponseEntity.ok(service.buscarPacientes(request));
	}
	
}
