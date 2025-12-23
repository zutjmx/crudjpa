package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
