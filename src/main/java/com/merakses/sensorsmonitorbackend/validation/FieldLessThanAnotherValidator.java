package com.merakses.sensorsmonitorbackend.validation;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldLessThanAnotherValidator implements ConstraintValidator<FieldLessThanAnother, Object> {

    private String from;

    private String to;

    @Override
    public void initialize(FieldLessThanAnother constraintAnnotation) {
        this.from = constraintAnnotation.from();
        this.to = constraintAnnotation.to();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Integer fromValue = (Integer) new BeanWrapperImpl(value).getPropertyValue(from);
        Integer toValue = (Integer) new BeanWrapperImpl(value).getPropertyValue(to);

        if (fromValue != null && toValue != null) {
            return fromValue < toValue;
        }

        return true;
    }
}
