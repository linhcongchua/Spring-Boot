package com.learn.meme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    private static final String PHONE_PATTERN="^+84[0-9]{9}$";
    private static final Pattern PATTERN=Pattern.compile(PHONE_PATTERN);

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        Matcher matcher=PATTERN.matcher(phone);
        return matcher.matches();
    }
}
