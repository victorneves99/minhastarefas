package br.com.victor.minhastarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.minhastarefas.controller.assembler.TarefaModelAssembler;
import br.com.victor.minhastarefas.controller.assembler.UsuarioModelAssembler;
import br.com.victor.minhastarefas.controller.request.UsuarioRequest;
import br.com.victor.minhastarefas.controller.response.UsuarioResponse;
import br.com.victor.minhastarefas.model.Usuario;
import br.com.victor.minhastarefas.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioModelAssembler assembler;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<UsuarioResponse> listUsuarios() {

        List<Usuario> usuarios = service.getUsuarios();

        List<UsuarioResponse> usuaResponses = usuarios.stream()
                .map(usuario -> mapper.map(usuario, UsuarioResponse.class)).collect(Collectors.toList());

        return usuaResponses;
    }

    @GetMapping("/{id}")
    public UsuarioResponse getUsuarioPorId(@PathVariable Integer id) {

        Usuario usuario = service.getUsuarioPorId(id);

        UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);

        return usuarioResponse;

    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioResponse>> criarUsuario(
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);

        Usuario usuarioSalvo = service.criarUsuario(usuario);

        EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioSalvo);

        return ResponseEntity.created(usuarioModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(usuarioModel);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Integer id) {

        service.deletarUsuario(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponse>> atualizarUsuario(@PathVariable Integer id,
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);

        Usuario usuarioSalvo = service.atualizar(id, usuario);

        EntityModel<UsuarioResponse> uModel = assembler.toModel(usuarioSalvo);

        return ResponseEntity.ok().body(uModel);

    }

}
