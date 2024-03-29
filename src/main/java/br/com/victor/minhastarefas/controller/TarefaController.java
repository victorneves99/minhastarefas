package br.com.victor.minhastarefas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.minhastarefas.controller.assembler.TarefaModelAssembler;
import br.com.victor.minhastarefas.controller.request.TarefaRequest;
import br.com.victor.minhastarefas.controller.response.TarefaResponse;
import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.services.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TarefaModelAssembler assembler;


    @GetMapping
    public CollectionModel<EntityModel<TarefaResponse>> todasTarefas(@RequestParam Map<String, String> parametros) {

        List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = service.getTodasTarefas();
        } else {
            String descricao = parametros.get("descricao");
            tarefas = service.getTarefasPorDescricao(descricao);
        }

        List<EntityModel<TarefaResponse>> tarefasModel = tarefas.stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tarefasModel,
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).todasTarefas(new HashMap<>()))
                        .withSelfRel());

    }

    @GetMapping("/{id}")
    public EntityModel<TarefaResponse> umaTarefa(@PathVariable Integer id) {

        Tarefa tarefa = service.getTarefaPorId(id);

        return assembler.toModel(tarefa);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TarefaResponse>> salvarTarefa(@RequestBody @Valid TarefaRequest tarefaRequest) {

        Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);
        Tarefa salvarTarefa = service.salvarTarefa(tarefa);
        EntityModel<TarefaResponse> tarefaModel = assembler.toModel(salvarTarefa);
        return ResponseEntity.created(tarefaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tarefaModel);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Integer id) {

        service.deleteById(id);

    }

    @PutMapping("/{id}/iniciar")
    public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Integer id) {

        Tarefa tarefa = service.iniciarTarefaPorId(id);

        return assembler.toModel(tarefa);

    }

    @PutMapping("/{id}/concluir")
    public EntityModel<TarefaResponse> concluirTarefa(@PathVariable Integer id) {

        Tarefa tarefa = service.concluirTarefaPorId(id);

        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/cancelar")
    public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable Integer id) {

        Tarefa tarefa = service.cancelarTarefaPorId(id);

        return assembler.toModel(tarefa);
    }

}
