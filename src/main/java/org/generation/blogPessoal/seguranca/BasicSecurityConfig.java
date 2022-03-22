package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // -->Habilitar a segurança
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userdtaDetailsService;

	@Override // -->usada para métodos sobrepostos
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // --> auth = senha de autorização.
																					// AuthenticationManagerBuilder é um padrão antes do auth.
																// throws = poder lançar exceções,levar um erro adiante.
		auth.userDetailsService(userdtaDetailsService);

							
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER");
			
	}

	@Bean //--. permitir que outras classes ultilize esse objeto.
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuario/logar").permitAll() //-->liberar caminhos dentro do controller sem a necessidade e se passar uma chave token
		.antMatchers("/usuario/cadastrar").permitAll()
		.anyRequest().authenticated() //--> todas as outras requisições deverão ser autenticadas.
		.and().httpBasic() //--> gerar chane token
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  //-->indicar qual é o tipo de seção a ser utilizada. //STATELESS = não guarda seção.
		.and().cors()
		.and().csrf().disable();
	}

}
