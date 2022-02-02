package br.com.victor.minhastarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.victor.minhastarefas.controller.TarefaCategoriaController;
import br.com.victor.minhastarefas.controller.TarefaController;
import br.com.victor.minhastarefas.controller.UsuarioController;
import br.com.victor.minhastarefas.controller.response.TarefaResponse;
import br.com.victor.minhastarefas.model.Tarefa;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {

        TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);

        EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse,
                linkTo(methodOn(TarefaController.class).umaTarefa(tarefaResponse.getId()))
                        .withSelfRel(),
                linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>()))
                        .withRel("tarefas"),
                linkTo(methodOn(TarefaCategoriaController.class).umaCategoria(tarefaResponse.getCategoriaId()))
                        .withRel("categoria"),
                linkTo(methodOn(UsuarioController.class).getUsuarioPorId(tarefaResponse.getUsuarioId()))
                        .withRel("usuario"));

        return tarefaModel;
    }

}
