package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsDb {
    
    String message() default "Ya existe un registro con este valor.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
