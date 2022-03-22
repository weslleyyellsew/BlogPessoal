package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Para que as classes sejam interpretadas no banco de dados tem uqe inserir parametros sobre as classes(@).

@Entity//--> é utilizada para informar que uma classe também é uma entidade(Uma entidade representauma tabela do banco de dados, e cada instância dessa entidade representa uma linha dessa tabela.). --Essa classe vai ser uma entidade  do JPA reverdate.
@Table(name = "postagem")//Essa entidade virara uma tabela no bancos de dados e estipular um nome a ela.

public class Postagem {

	//Atributos da classe/ Variaveis.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//-->Gerar um valor e tonar uma Primery Key.
	private long id;//Sempre iniciair com o id.
	//--Os atributos das 3 linhas acima vai se tornar um Primery Key

	
	@NotBlank//--> não pode ficar vazio, necessário ser preenchido.
	@Size (min = 5, max =100)//--> Definir a quantidade de caracteres permitido.
	private String titulo;
	
	@NotBlank
	@Size(min = 10, max =500)
	private String texto;
	
	
	@Temporal(TemporalType.TIMESTAMP)//-->Identificar que esta ttrabalhando com tempo e qualtipo de tempo.
	//TIMESTAMP = Retorna um carimbo de data/hora contendo uma data como uma data de calendário e uma hora como uma hora do dia.
	private Date date = new java.sql.Date(System.currentTimeMillis());//--> Importar biblioteca...java.util.Date //Captar as informações de qundo foi postado (dia, mes, ano, hora, segundo )
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")  //parar loop infinito
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
