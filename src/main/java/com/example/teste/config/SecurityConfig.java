package com.example.teste.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.teste.modelo.PerfilTipo;
import com.example.teste.service.UsuarioService;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String MEDICO = PerfilTipo.MEDICO.getDesc();
    private static final String PACIENTE = PerfilTipo.PACIENTE.getDesc();	
	@Autowired
	private UsuarioService service;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			// acessos públicos liberados
			.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
			.antMatchers("/", "/home").permitAll()
			
			// acessos privados admin
			.antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(PACIENTE, MEDICO, ADMIN)
			.antMatchers("/u/**").hasAuthority(ADMIN)
			
			// acessos privados medicos
			.antMatchers("/medicos/especialidade/titulo/*").hasAnyAuthority(PACIENTE, MEDICO)
			.antMatchers("/medicos/dados", "/medicos/salvar", "/medicos/editar").hasAnyAuthority(MEDICO, ADMIN)
			.antMatchers("/medicos/**").hasAnyAuthority(MEDICO, ADMIN)
			.antMatchers("/medicos/titulos").hasAuthority(ADMIN)
			
			// acessos privados pacientes
			.antMatchers("/pacientes/**").hasAnyAuthority(PACIENTE, ADMIN, MEDICO)
			// acesso privado de funcionario a gravar pacientes
			.antMatchers("/pacientes/gravarPaciente").hasAuthority(ADMIN)
			.antMatchers("/pacientes/editar").hasAuthority(ADMIN)
			
			// acessos privados funcionario
			.antMatchers("/funcionarios/**").hasAuthority(ADMIN)
			
			// acessos privados especialidades
			.antMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(MEDICO, ADMIN)
			.antMatchers("/especialidades/titulo").hasAnyAuthority(MEDICO, ADMIN, PACIENTE)
			.antMatchers("/especialidades/**").hasAuthority(ADMIN)
			// acesso ai tipo de exame
			.antMatchers("/tipoExames/**").hasAnyAuthority(ADMIN, MEDICO)
			// acesso ai tipo de exame
			.antMatchers("/pedidoExames/abrir").hasAnyAuthority(ADMIN, MEDICO)
			
			
			.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.failureUrl("/login-error")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/acesso-negado");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	

}
