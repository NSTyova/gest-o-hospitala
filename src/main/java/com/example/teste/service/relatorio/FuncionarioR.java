package com.example.teste.service.relatorio;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


@Service
public class FuncionarioR {

	private static final String JASPER_DIRETORIO="classpath:jasper/";
	private static final String JASPER_PREFIXO="funcionario3";
	private static final String JASPER_SUFIXO=".jasper";
	
	@Autowired
	public Connection connection;

	private Map<String, Object> params = new HashMap<>();
	
	public void addParams(String key, Object value) {
		this.params.put(key, value)
;	}
	
	@SuppressWarnings("unused")
	public byte[] exportarPDF(String code) {
		byte[] bytes = null;
		try {
			System.out.println("começou aqui 1");
			File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
			System.out.println("começou aqui 2");
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
			System.out.println("começou aqui 3");
			bytes= JasperExportManager.exportReportToPdf(print);
			System.out.println("começou aqui 4");
		} catch (IOException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
}
