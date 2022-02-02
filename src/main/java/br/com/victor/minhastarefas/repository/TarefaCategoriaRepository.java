package br.com.victor.minhastarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.minhastarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria,Integer>{

    public List<TarefaCategoria> findByNomeLike(String nome);
    
}
