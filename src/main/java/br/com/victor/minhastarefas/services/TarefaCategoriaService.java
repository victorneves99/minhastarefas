package br.com.victor.minhastarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.minhastarefas.model.TarefaCategoria;
import br.com.victor.minhastarefas.repository.TarefaCategoriaRepository;

@Service
public class TarefaCategoriaService {

    @Autowired
    private TarefaCategoriaRepository repository;

    public List<TarefaCategoria> getCategoriaParam(String categoria) {

        List<TarefaCategoria> categorias = repository.findByNomeLike("%" + categoria + "%");

        return categorias;

    }

    public List<TarefaCategoria> getAllCategorias() {

        return repository.findAll();

    }

    public TarefaCategoria getCategoriaPorId(Integer id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

    }

    public TarefaCategoria postCategoria(TarefaCategoria categoria) {

        TarefaCategoria save = repository.save(categoria);

        return save;

    }

    public void deleteCategoria(Integer id) {

        repository.deleteById(id);

    }

}
