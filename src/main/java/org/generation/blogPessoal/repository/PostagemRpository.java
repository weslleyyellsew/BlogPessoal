package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository//--> Indicar para o String que tipo de classe é...
public interface PostagemRpository extends JpaRepository<Postagem, Long> {//--> extend = herdar <oq eu quero exportar , tipo do ID primitivo"letra maiuscula">

	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);//-->findAllBy"nome do atributo da entidade" = seleciona tudo q está no atributo // findAll = seleciona todos
																				// Containing = trazer todos os caracteres
																				// IgnoreCase = ignorarar maiusculo ou minusculo
}
