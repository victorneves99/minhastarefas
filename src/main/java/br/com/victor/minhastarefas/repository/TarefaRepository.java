package br.com.victor.minhastarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.minhastarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
