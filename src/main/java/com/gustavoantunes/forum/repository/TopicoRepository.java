package com.gustavoantunes.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavoantunes.forum.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

	/**
	 * Este método retorno tópicos pesquisando dentro do relacionamento da entidade Curso
	 * através do nome, caso houvesse um atributo dentro do Topico em que se chamasse cursoNome,
	 * o hibernate filtraria pelo atributo da classe e não do relacionamento, para resolver isso 
	 * poderia utilizar o metodo da seguinte maneira separando com o espaço
	 * List<Topico> findByCurso_Nome(String nomeCurso);
	 * @param nomeCurso
	 * @return List<Topico>
	 */
	List<Topico> findByCursoNome(String nomeCurso);

}
