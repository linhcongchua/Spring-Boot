package com.learn.meme.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface ValidPhone {
    String message() default "Invalid Phone! Example +84971741127";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
