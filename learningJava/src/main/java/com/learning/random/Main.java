package com.learning.random;

import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {

    Stream.generate(
            () -> {
              return generateRandomNumbersWithRange('A', 'Z' + 1);
            })
        .limit(10)
        .forEach(x -> System.out.printf("%1$d = %1$c%n", x));
  }

  public static Integer generateRandomNumbersWithRange(int lowerBound, int upperBound) {
    // Getting range of numbers (int) (Math.random() * (upper - lower)) + lower
    int randomInt = (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    return Integer.valueOf(randomInt);
  }
}
