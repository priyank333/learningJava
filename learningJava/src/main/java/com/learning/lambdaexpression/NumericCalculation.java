package com.learning.lambdaexpression;

import java.util.Random;

/**
 * Lambda expressions are introduced in Java 8 and are touted to be the biggest feature of Java 8.
 * Lambda expression facilitates functional programming, and simplifies the development a lot.
 */
public class NumericCalculation {
  public static void main(String[] args) {
    MathOperation addition = (a, b) -> a + b;
    MathOperation subtraction =
        (a, b) -> {
          return a - b;
        };
    MathOperation multiplication =
        (a, b) -> {
          return a * b;
        };
    MathOperation division = (a, b) -> a / b;
    Random random = new Random();
    int a = random.nextInt(100);
    int b = random.nextInt(100);
    System.out.println("Addition of " + a + " and " + b + " is " + addition.operation(a, b));
    System.out.println("Subtraction of " + a + " and " + b + " is " + subtraction.operation(a, b));
    System.out.println(
        "Multiplication of " + a + " and " + b + " is " + multiplication.operation(a, b));
    System.out.println("Division of " + a + " and " + b + " is " + division.operation(a, b));
  }
}
