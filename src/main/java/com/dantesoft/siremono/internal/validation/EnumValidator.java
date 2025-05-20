package com.dantesoft.siremono.internal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
  private Enum<?>[] enumValues;

  @Override
  public void initialize(ValidEnum constraintAnnotation) {
    enumValues = constraintAnnotation.enumClass().getEnumConstants();
  }

  @Override
  public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return Arrays.asList(enumValues).contains(value);
  }
}
