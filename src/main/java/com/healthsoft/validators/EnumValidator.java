package com.healthsoft.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValidEnum annotation) {
        this.acceptedValues = Stream.of(annotation.type().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        return this.acceptedValues.contains(value);
    }
}
