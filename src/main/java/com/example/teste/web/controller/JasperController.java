package com.example.teste.web.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teste.service.relatorio.JasperService;

@Controller
public class JasperController {

	@Autowired
	private JasperService service;
	
	

	@GetMapping("/relatorio/pdf/jr1")
	public void exibirRelatorio(@RequestParam("code") String code, @RequestParam("acao") String acao,
			HttpServletResponse response) {
		byte[] bytes = service.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
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
	
	@GetMapping("/relatorio/pdf/jr2/{code}")
	public void exibirRelatorio1(@PathVariable("code") String code,
			@RequestParam(name="asc",required=false) LocalDate asc,
			@RequestParam(name = "desc", required = false) LocalDate desc,
			HttpServletResponse response) throws IOException {
		
		service.addParams("NEVEL_DESC",  desc);
		service.addParams("Nivel_asc", asc);
		byte[] bytes = service.exportarPDF(code);
		System.out.println("chegou aqui");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "inline; filename=relatorio" + code + ".pdf");
		response.getOutputStream().write(bytes);
		
	}
	
	
	
}
