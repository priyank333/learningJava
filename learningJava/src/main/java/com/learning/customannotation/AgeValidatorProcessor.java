package com.learning.customannotation;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AgeValidatorProcessor {
  public static void validateAge(Object obj) {
    Field[] fields = obj.getClass().getDeclaredFields();
    Arrays.stream(fields)
        .forEach(
            field -> {
              if (field.isAnnotationPresent(AgeValidator.class)) {
                field.setAccessible(true);
                try {
                  int age = field.getInt(obj);
                  AgeValidator annotation = field.getAnnotation(AgeValidator.class);
                  if (age < annotation.min() || age > annotation.max()) {
                    throw new IllegalArgumentException(
                        "Age must be between %d to %d"
                            .formatted(annotation.min(), annotation.max()));
                  }
                } catch (IllegalAccessException illegalArgumentException) {

                }
              }
            });
  }
}
