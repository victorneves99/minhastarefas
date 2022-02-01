package br.com.victor.minhastarefas.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> getTodasTarefas() {
        return repository.findAll();
    }

    public List<Tarefa> getTarefasPorDescricao(String descricao) {
        return repository.findByDescricaoLike("%" + descricao + "%");
    }

    public Tarefa getTarefaPorId(Integer id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {

        return repository.save(tarefa);

    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
