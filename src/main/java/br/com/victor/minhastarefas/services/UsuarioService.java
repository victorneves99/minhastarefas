package br.com.victor.minhastarefas.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victor.minhastarefas.controller.response.JwtResponse;
import br.com.victor.minhastarefas.model.Role;
import br.com.victor.minhastarefas.model.Usuario;
import br.com.victor.minhastarefas.repository.RoleRepository;
import br.com.victor.minhastarefas.repository.UsuarioRepository;
import br.com.victor.minhastarefas.security.JwtUtils;
import br.com.victor.minhastarefas.security.UserDetailsImpl;

import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

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

    public Usuario atualizar(Integer id, Usuario usuario) {

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

    public JwtResponse autenticaUsuario(String nome, String senha) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(nome, senha));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.genereteJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String>   roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(),userDetails.getUsername(),roles);
    }

}
