package com.learning.methodreference;

/**
 * Method reference to an instance method of a particular object of a class
 * <p>
 * This type of method reference refers to non-static instance methods by a class object.
 */
public class InstanceMethodReference {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Display display = printer::displayImplementation;
        display.display("Hello Priyank ...");
    }
}

class Printer {
    public void displayImplementation(String message) {
        System.out.println(message);
    }
}

/**
 * An Interface that contains exactly one abstract method is known as functional interface.
 * It can have any number of default, static methods but can contain only one abstract method.
 * It can also declare methods of object class.
 * <p>
 * Functional Interface is also known as Single Abstract Method Interfaces or SAM Interfaces.
 * It is a new feature in Java, which helps to achieve functional programming approach.
 */
@FunctionalInterface
interface Display {
    void display(String message);
}