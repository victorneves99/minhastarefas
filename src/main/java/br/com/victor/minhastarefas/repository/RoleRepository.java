package br.com.victor.minhastarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.minhastarefas.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    
}
