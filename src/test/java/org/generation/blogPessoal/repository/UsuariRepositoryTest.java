package org.generation.blogPessoal.repository;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuariRepositoryTest {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	
	@BeforeAll
	void start(){
		usuarioRepository.save(new Usuario (0L, "Pinguelo de borboleta", "Htinhavoltou@gmail.com", "123321123"));
		usuarioRepository.save(new Usuario (0L, "Rabinho de casoleta", "Barbienhew@hotmail.com", "321123321"));
		usuarioRepository.save(new Usuario (0L, "Ladyganas ", "Ladygagas@hotmail.com", "badromace"));

	}
	
	@Test
	@DisplayName("Retornar apenas um usuario")
	public void deveRetornarUmUsuario () {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("Barbienhew@hotmail.com");
				assertTrue(usuario.get().getUsuario().equals("Barbienhew@hotmail.com"));
	}
	
	@Test
	@DisplayName("Deve retorna 3 usuarios")
	
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("borboleta");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Pinguelo de borboleta"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Rabinho de casoleta"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Ladyganas"));
	}
	@AfterAll
	
	public void end() {
		usuarioRepository.deleteAll();
	}

}
