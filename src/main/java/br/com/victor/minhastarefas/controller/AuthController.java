package br.com.victor.minhastarefas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.minhastarefas.controller.request.LoginRequest;
import br.com.victor.minhastarefas.controller.response.JwtResponse;
import br.com.victor.minhastarefas.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {

        JwtResponse jwtResponse = usuarioService.autenticaUsuario(login.getNome(), login.getSenha());

        return ResponseEntity.ok(jwtResponse);
    }

}
