package br.com.victor.minhastarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.minhastarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria,Integer>{
    
}
