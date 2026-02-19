package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services.ProductoService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

    @Autowired
    private ProductoService productoService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { 
        if (productoService == null) {
            return true; // Si el servicio no está disponible, consideramos la validación como válida para evitar errores de null pointer
        }
        return !productoService.existsBySku(value);
    }


}
