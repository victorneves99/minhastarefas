package br.com.victor.minhastarefas.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victor.minhastarefas.model.Role;
import br.com.victor.minhastarefas.model.Usuario;
import br.com.victor.minhastarefas.repository.RoleRepository;
import br.com.victor.minhastarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getUsuarios() {

        return repository.findAll();

    }

    public Usuario getUsuarioPorId(Integer id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

    }

    public Usuario criarUsuario(Usuario usuario) {

        Set<Role> roles = getRoles(usuario);
        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);

    }

    public void deletarUsuario(Integer id) {
        repository.deleteById(id);
    }


    public Usuario atualizar(Integer id,Usuario usuario){

        Optional<Usuario> usuarioBanco = repository.findById(id);

        if (!usuarioBanco.isPresent()) {
            throw new EntityNotFoundException();
        }

        usuario.setId(id);

        return criarUsuario(usuario);
    }



    private Set<Role> getRoles(Usuario usuario) {
        Set<Role> rolesBanco = usuario.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .collect(Collectors.toSet());

        return rolesBanco;
    }

}
