package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.validation.IsExistsDb;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{NotEmpty.producto.nombre}")
    @Size(min = 4, max = 50, message = "{Size.producto.nombre}")
    private String nombre;

    @Min(value = 10, message = "{Min.producto.precio}")
    @NotNull(message = "{NotNull.producto.precio}")
    private Double precio;

    //@NotEmpty(message = "{NotEmpty.producto.descripcion}")
    @IsRequired
    @Size(min = 10, max = 200, message = "{Size.producto.descripcion}")
    private String descripcion;

    //@IsRequired
    //@IsExistsDb
    private String sku;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
