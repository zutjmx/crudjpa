package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services;

import java.util.List;
import java.util.Optional;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Producto;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    Producto update(Long id, Producto producto);
    void delete(Producto producto);
    void deleteById(Long id);
}
