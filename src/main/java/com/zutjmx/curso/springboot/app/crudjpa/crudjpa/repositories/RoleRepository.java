package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByNombre(String nombre);
    
}
