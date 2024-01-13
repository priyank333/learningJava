package com.learning.methodreference;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * This method reference is used to refer to a static method that is defined in a class.
 */
public class StaticMethodReference {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> operation;
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        operation = Operations::addition;
        System.out.println("Addition of " + a + " and " + b + " is : " + operation.apply(a, b));
        operation = Operations::subtraction;
        System.out.println("Subtraction of " + a + " and " + b + " is : " + operation.apply(a, b));
        operation = Operations::multiply;
        System.out.println("Multiplication of " + a + " and " + b + " is : " + operation.apply(a, b));
        operation = Operations::division;
        System.out.println("Division of " + a + " and " + b + " is : " + operation.apply(a, b));
    }
}

class Operations {
    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int division(int a, int b) {
        return a / b;
    }

    public static int addition(int a, int b) {
        return a + b;
    }

    public static int subtraction(int a, int b) {
        return a - b;
    }
}
