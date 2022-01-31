package br.com.victor.minhastarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.minhastarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    public List<Tarefa> findByDescricao(String descricao);
    public List<Tarefa> findByDescricaoLike(String descricao);

}
