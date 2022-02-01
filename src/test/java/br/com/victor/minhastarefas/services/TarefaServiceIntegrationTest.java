package br.com.victor.minhastarefas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.victor.minhastarefas.exception.TarefaStatusException;
import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.model.TarefaStatus;

@SpringBootTest
public class TarefaServiceIntegrationTest {

    @Autowired
    private TarefaService service;

    @Test
    void deveIniciarTarefa() {

        Tarefa tarefa = service.iniciarTarefaPorId(3);
        assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());

    }

    @Test
    void naoDeveIniciarTarefaConcluida() {

        Tarefa tarefa = service.getTarefaPorId(3);
        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        service.salvarTarefa(tarefa);

        assertThrows(TarefaStatusException.class, () -> service.iniciarTarefaPorId(3));

    }

}
