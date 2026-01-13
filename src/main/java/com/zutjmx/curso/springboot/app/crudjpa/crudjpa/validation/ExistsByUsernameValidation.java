package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services.UsuarioService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {        
        return !usuarioService.existsByUsername(username);
    }

}
