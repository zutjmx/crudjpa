package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty() || value.trim().isBlank()) {
            return false;
        }
        return true;
    }

}
