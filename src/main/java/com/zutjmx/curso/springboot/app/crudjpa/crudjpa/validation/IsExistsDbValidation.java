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
        
        // Aquí se debería implementar la lógica para verificar si el valor existe en la base de datos.
        // Por simplicidad, retornamos false para indicar que el valor ya existe.
        return value != null && !productoService.existsBySku(value);
    }


}
