package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Producto;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services.ProductoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Producto> optionalProducto = productoService.findById(id);
        if (optionalProducto.isPresent()) {
            return ResponseEntity.ok(optionalProducto.orElseThrow());            
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> crear(
        @Valid @RequestBody Producto producto, 
        BindingResult result
    ) {
        if (result.hasFieldErrors()) {
            return manejarErrores(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));        
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(        
        @Valid @RequestBody Producto producto, 
        BindingResult result,
        @PathVariable Long id
    ) {
        if (result.hasFieldErrors()) {
            return manejarErrores(result);
        }
        Optional<Producto> optionalProducto = productoService.findById(id);
        if (!optionalProducto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.update(id,producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Optional<Producto> optionalProducto = productoService.findById(id);
        if (optionalProducto.isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.ok(optionalProducto.orElseThrow());            
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> manejarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error en el campo " + err.getField() + ", " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }
    
}
