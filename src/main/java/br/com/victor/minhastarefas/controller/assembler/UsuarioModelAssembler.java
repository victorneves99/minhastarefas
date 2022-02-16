package br.com.victor.minhastarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.victor.*;
import br.com.victor.minhastarefas.controller.UsuarioController;
import br.com.victor.minhastarefas.controller.response.UsuarioResponse;
import br.com.victor.minhastarefas.model.Usuario;

@Component
public class UsuarioModelAssembler implements
        RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<UsuarioResponse> toModel(Usuario usuario) {
        UsuarioResponse usuarioResp = mapper.map(usuario, UsuarioResponse.class);

        EntityModel<UsuarioResponse> usuarioModel = EntityModel.of(usuarioResp,
                linkTo(
                        methodOn(UsuarioController.class).getUsuarioPorId(usuarioResp.getId()))
                                .withSelfRel());

        return usuarioModel;
    }

}
