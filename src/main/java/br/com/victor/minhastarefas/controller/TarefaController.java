package br.com.victor.minhastarefas.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.services.TarefaService;

@RestController
@RequestMapping
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping("/tarefa")
    public List<Tarefa> todasTarefas(@RequestParam Map<String, String> parametros) {
        if (parametros.isEmpty()) {
            return service.getTodasTarefas();
        }

        String descricao = parametros.get("descricao");
        return service.getTarefasPorDescricao(descricao);

    }

    @GetMapping("/tarefa/{id}")
    public Tarefa umaTarefa(@PathVariable Integer id) {
        return service.getTarefaPorId(id);
    }

    @PostMapping("/tarefa")
    public Tarefa salvarTarefa(@RequestBody @Valid Tarefa tarefa) {

        return service.salvarTarefa(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void escluirTarefa(@PathVariable Integer id) {

        service.deleteById(id);

    }
}
