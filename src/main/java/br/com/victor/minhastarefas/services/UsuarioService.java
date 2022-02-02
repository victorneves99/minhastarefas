package br.com.victor.minhastarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.minhastarefas.model.Usuario;
import br.com.victor.minhastarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getUsuarios() {

        return repository.findAll();

    }

    public Usuario getUsuarioPorId(Integer id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

    }

    public Usuario criarUsuario(Usuario usuario) {

        return repository.save(usuario);

    }

    public void deletarUsuario(Integer id) {
        repository.deleteById(id);
    }

}
