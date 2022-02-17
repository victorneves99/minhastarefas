package br.com.victor.minhastarefas.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.victor.minhastarefas.model.ERole;
import br.com.victor.minhastarefas.model.Role;
import br.com.victor.minhastarefas.model.Tarefa;
import br.com.victor.minhastarefas.model.TarefaCategoria;
import br.com.victor.minhastarefas.model.TarefaStatus;
import br.com.victor.minhastarefas.model.Usuario;
import br.com.victor.minhastarefas.repository.RoleRepository;
import br.com.victor.minhastarefas.repository.TarefaCategoriaRepository;
import br.com.victor.minhastarefas.repository.TarefaRepository;
import br.com.victor.minhastarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TarefaCategoriaRepository tarefaCategoriaRepository;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;

    @Bean
    CommandLineRunner executar(){



        return args -> {

            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleAdmin = roleRepository.save(roleAdmin);
           


            Usuario usuario = new Usuario();
            usuario.setNome("Admin");
            usuario.setSenha(encoder.encode("123456"));
            usuario.setRoles(Set.of(roleAdmin));
            usuarioRepository.save(usuario);
            


            TarefaCategoria categoria = new TarefaCategoria();
            categoria.setNome("Estudos");
            tarefaCategoriaRepository.save(categoria);

            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao("Aprender Spring Boot");
            tarefa.setDataEntrega(LocalDate.now().plusDays(1));
            tarefa.setStatus(TarefaStatus.ABERTO);
            tarefa.setVisivel(true);
            tarefa.setCategoria(categoria);
            tarefa.setUsuario(usuario);
            tarefaRepository.save(tarefa);
        };

    }

}
