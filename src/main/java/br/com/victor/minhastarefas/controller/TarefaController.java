package br.com.victor.minhastarefas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.minhastarefas.controller.request.TarefaRequest;
import br.com.victor.minhastarefas.controller.response.TarefaResponse;
import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.services.TarefaService;

@RestController
@RequestMapping
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/tarefa")
    public List<TarefaResponse> todasTarefas(@RequestParam Map<String, String> parametros) {

        List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = service.getTodasTarefas();
        } else {
            String descricao = parametros.get("descricao");
            tarefas = service.getTarefasPorDescricao(descricao);
        }

        List<TarefaResponse> tarefaResponses = tarefas.stream().map(tarefa -> mapper.map(tarefa, TarefaResponse.class))
                .collect(Collectors.toList());

        return tarefaResponses;

    }

    @GetMapping("/tarefa/{id}")
    public TarefaResponse umaTarefa(@PathVariable Integer id) {

        Tarefa tarefa = service.getTarefaPorId(id);

        TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);

        return tarefaResponse;
    }

    @PostMapping("/tarefa")
    public TarefaResponse salvarTarefa(@RequestBody @Valid TarefaRequest tarefaRequest) {

        Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);
        TarefaResponse tarefaReq = mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
        return tarefaReq;
    }

    @DeleteMapping("/tarefa/{id}")
    public void escluirTarefa(@PathVariable Integer id) {

        service.deleteById(id);

    }
}
