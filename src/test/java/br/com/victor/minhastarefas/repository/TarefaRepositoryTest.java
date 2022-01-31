package br.com.victor.minhastarefas.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.victor.minhastarefas.model.Tarefa;

@SpringBootTest
public class TarefaRepositoryTest {
    
    @Autowired
    private TarefaRepository repository;

    @Test
    void testFindByNomeCategoria(){

        List<Tarefa> tarefas = repository.findByNomeCategoria("Estudos");
        Assertions.assertEquals(1, tarefas.size());

    }
    @Test
    void testTarefasPorCategoria(){

        List<Tarefa> tarefas = repository.tarefasPorCategoria("Estudos");
        Assertions.assertEquals(1, tarefas.size());

    }
}
