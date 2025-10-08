package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
