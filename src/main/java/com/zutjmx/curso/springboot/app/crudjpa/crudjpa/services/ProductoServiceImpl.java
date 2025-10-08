package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Producto;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll() {        
        return (List<Producto>) productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> findById(Long id) {        
        return productoRepository.findById(id);
    }

    @Transactional
    @Override
    public Producto save(Producto producto) {        
        return productoRepository.save(producto);
    }

    @Transactional
    @Override
    public void delete(Producto producto) {   
        Optional<Producto> productoDb = productoRepository.findById(producto.getId());
        productoDb.ifPresent(p -> {
            productoRepository.delete(p);
        });    
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Producto> productoDb = productoRepository.findById(id);
        productoDb.ifPresent(p -> {
            productoRepository.deleteById(p.getId());
        });        
    }

}
