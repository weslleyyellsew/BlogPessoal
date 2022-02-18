package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Entity //--Essa classe vai ser uma entidade  do JPA reverdate.
@Table(name = "postagem") //--Essa entidade dentro do banco de dados vai virar uma tabela


public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
//--Os atributos das 3 linhas acima vai se tornar um Primery Key

	@NotBlank
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotBlank
	@Size(min = 10, max = 500)
	private String texto;
	
	@Temporal (TemporalType.DATE)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	};
	
	
	
}
