package br.com.victor.minhastarefas.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.victor.minhastarefas.exception.TarefaStatusException;
import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.model.TarefaStatus;
import br.com.victor.minhastarefas.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    @Test
    void naoDeveConcluirTarefaCancelada() {

        Integer idExemplo = 1;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idExemplo);
        tarefa.setDescricao("Teste 01");
        tarefa.setStatus(TarefaStatus.CANCELADA);

        Mockito.when(repository.findById(idExemplo)).thenReturn(Optional.of(tarefa));

        assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(idExemplo));

    }

}
