package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> CadastroUsuario (Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha()); //--> pssar a senha ue veio dentro do objeto usuario.
		usuario.setSenha(senhaEncoder);//--> senha cripitada.
		
		 return Optional.of(repository.save(usuario));//--> salva a senha já Encriptado.
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());//-->fazer a pesquisa pelo nome do usuario.

	if(usuario.isPresent()) {
		if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { //--> se tem algo dentro do usuario comparar a senha criptografada com a senha digitada.
			
			String auth = user.get().getUsuario() + ":" + user.get().getSenha();
			
			byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encoderAuth);
			
			user.get().setToken(authHeader);
			user.get().setNome(usuario.get().getNome()); 
			
			return user;
		}
	}
	return null;	
	}
}
