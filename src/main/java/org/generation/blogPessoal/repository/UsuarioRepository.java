package org.generation.blogPessoal.repository;//-->ligação da API com a base de dados.

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional <Usuario> findByUsuario (String usuario);
	
	//Método criado para a Sessão de testes
			public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}
