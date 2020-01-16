package com.gustavoantunes.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoantunes.forum.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
}
