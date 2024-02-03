package com.learning.customannotation;

public class Main {
  public static void main(String[] args) {
    Person person = new Person("Priyank", 17);
    AgeValidatorProcessor.validateAge(person);
    System.out.println("Age is valid");
  }
}
