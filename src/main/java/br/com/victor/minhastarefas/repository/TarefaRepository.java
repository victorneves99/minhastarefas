package br.com.victor.minhastarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.model.TarefaCategoria;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    public List<Tarefa> findByDescricao(String descricao);

    public List<Tarefa> findByDescricaoLike(String descricao);

    public List<Tarefa> findByCategoria(TarefaCategoria categoria);

    @Query("select t from Tarefa t inner join t.categoria c where c.nome = ?1")
    public List<Tarefa> findByNomeCategoria(String nomeCategoria);

    public List<Tarefa> tarefasPorCategoria(String nomeCategoria);

}
