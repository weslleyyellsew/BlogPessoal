package org.generation.blogPessoal.model; //-->entidade que salva na base de dados.

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity //-->criar uma tabela
@Table (name = "tb_usuario") //-->nomear a tabela
public class Usuario {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull  (message = "Nome não inserido, atributo obrigatório")
	@Size (min = 2, max = 100)
	private String nome;
	
	@Schema(example = "email@email.com.br")
	@NotNull (message = "Usuario não inserido, atributo obrigatório")
	@Size (min = 5, max = 100)
	@Email
	private String usuario;
	
	@NotNull
	@Size (min = 5, message = "A senha tem que ter de 6 caracteres", max = 100)
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List <Postagem> postagem;
	
	public Usuario(Long id, String nome, String usuario, String senha) {
	
	this.id = id;
	this.nome = nome;
	this.usuario = usuario;
	this.senha = senha;
	}
	
	public Usuario() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
