package com.example.teste.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teste.modelo.Funcionario;
import com.example.teste.modelo.Usuario;
import com.example.teste.service.FuncionarioService;
import com.example.teste.service.UsuarioService;
import com.example.teste.service.relatorio.FuncionarioR;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FuncionarioR funcionarioR;
	
	// abrir pagina de dados pessoais do paciente
	@GetMapping("/dados")
	public String cadastrar(Funcionario funcionario, ModelMap model, @AuthenticationPrincipal User user) {
		funcionario = service.buscarPorUsuarioEmail(user.getUsername());
		if (funcionario.hasNotId()) {
			funcionario.setUsuario(new Usuario(user.getUsername()));
		}
		model.addAttribute("funcionario", funcionario);
		return "funcionario/cadastro";
	}
	
	// salvar o form de dados pessoais do paciente com verificacao de senha
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, ModelMap model, @AuthenticationPrincipal User user) {
		Usuario u = usuarioService.buscarPorEmail(user.getUsername());
		if (UsuarioService.isSenhaCorreta(funcionario.getUsuario().getSenha(), u.getSenha())) {
			funcionario.setUsuario(u);
			service.salvar(funcionario);
			model.addAttribute("sucesso", "Seus dados foram inseridos com sucesso.");
		} else {
			model.addAttribute("falha", "Sua senha não confere, tente novamente.");
		}
		return "funcionario/cadastro";
	}	
	
	// editar o form de dados pessoais do paciente com verificacao de senha
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, ModelMap model, @AuthenticationPrincipal User user) {
		Usuario u = usuarioService.buscarPorEmail(user.getUsername());
		if (UsuarioService.isSenhaCorreta(funcionario.getUsuario().getSenha(), u.getSenha())) {
			service.editar(funcionario);
			model.addAttribute("sucesso", "Seus dados foram editados com sucesso.");
		} else {
			model.addAttribute("falha", "Sua senha não confere, tente novamente.");
		}
		return "funcionario/cadastro";
	}	
	
	// relatorios de lista dos funcionarios
	@GetMapping("/relatorio/pdf/jr2")
	public void exibirRelatorio(@RequestParam("code") String code, @RequestParam("acao") String acao,
			HttpServletResponse response) {
		System.out.println("chegou aqui 1");
		byte[] bytes = funcionarioR.exportarPDF(code);
		System.out.println("chegou aqui 2");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		System.out.println("chegou aqui 3");
		if (acao.equals("v")) {
			response.setHeader("Content-disposition", "inline; filename=relatorio" + code + ".pdf");
		} else {
			response.setHeader("Content-disposition", "attachment; filename=relatorio-" + code + ".pdf");
		}
		try {
			response.getOutputStream().write(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
