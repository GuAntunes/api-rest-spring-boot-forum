package com.gustavoantunes.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoantunes.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

}
