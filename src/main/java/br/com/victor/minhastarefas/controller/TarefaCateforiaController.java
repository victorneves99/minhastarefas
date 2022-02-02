package br.com.victor.minhastarefas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import br.com.victor.minhastarefas.controller.request.TarefaCategoriaRequest;
import br.com.victor.minhastarefas.controller.response.TarefaCategoriaResponse;
import br.com.victor.minhastarefas.model.TarefaCategoria;
import br.com.victor.minhastarefas.services.TarefaCategoriaService;

@RestController
@RequestMapping("/categoria")
public class TarefaCateforiaController {

    @Autowired
    private TarefaCategoriaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<TarefaCategoriaResponse> todasCategorias(@RequestParam Map<String, String> parametros) {

        List<TarefaCategoria> tarefaCategoria = new ArrayList<>();

        if (parametros.isEmpty()) {
            service.getAllCategorias();
        } else {

            String nome = parametros.get("nome");

            tarefaCategoria = service.getCategoriaParam("%" + nome + "%");
        }

        List<TarefaCategoriaResponse> categoriaResponse = tarefaCategoria.stream()
                .map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class))
                .collect(Collectors.toList());

        return categoriaResponse;

    }

    @GetMapping("/{id}")
    public TarefaCategoriaResponse umaCategoria(@PathVariable Integer id) {

        TarefaCategoria categoriaId = service.getCategoriaPorId(id);

        TarefaCategoriaResponse categoriaResponse = mapper.map(categoriaId, TarefaCategoriaResponse.class);

        return categoriaResponse;

    }

    @PostMapping
    public TarefaCategoriaResponse criarCategoria(@RequestBody TarefaCategoriaRequest tarefaCategoriaRequest) {

        TarefaCategoria categoria = mapper.map(tarefaCategoriaRequest, TarefaCategoria.class);
        return mapper.map(service.postCategoria(categoria), TarefaCategoriaResponse.class);

    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Integer id) {

        service.deleteCategoria(id);

    }
}
