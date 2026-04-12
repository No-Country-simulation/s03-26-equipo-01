package com.cms.controller.annotations;

import com.cms.controller.annotations.validator.ImageFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageFileValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageFile {
    String message() default "El archivo debe ser una imagen (jpg, jpeg, png, webp)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}