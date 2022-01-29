package br.com.victor.minhastarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.minhastarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    
}
