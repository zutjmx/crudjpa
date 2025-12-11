package com.zutjmx.curso.springboot.app.crudjpa.crudjpa;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Producto;

@Component
public class ProductoValidation implements Validator {

    @Override
    public boolean supports(@SuppressWarnings("null") Class<?> clazz) {
        return Producto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@SuppressWarnings("null") Object target, @SuppressWarnings("null") Errors errors) {
        Producto producto = (Producto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.producto.nombre");
        
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "NotEmpty.producto.descripcion");        
        if (producto.getDescripcion() == null || producto.getDescripcion().isBlank()) {
            errors.rejectValue("descripcion", "NotEmpty.producto.descripcion");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "NotNull.producto.precio");

        if (producto.getPrecio() != null && producto.getPrecio() <= 0) {
            errors.rejectValue("precio", "Min.producto.precio");
        }
        
    }

}
