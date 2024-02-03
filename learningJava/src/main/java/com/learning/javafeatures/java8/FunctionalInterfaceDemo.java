package com.learning.javafeatures.java8;

@FunctionalInterface
interface MyFunctionalInterface {
  void myMethod(String argument);
}

public class FunctionalInterfaceDemo {
  public static void main(String[] args) {
    Runnable runnable1 =
        () -> {
          System.out.println("Hello Runnable 1");
        };
    runnable1.run();
    MyFunctionalInterface runnable2 = MyClass::printMsg;
    runnable2.myMethod("Hello ...");
  }
}

class MyClass {
  public static void printMsg(String msg) {
    System.out.println("Msg: " + msg);
  }
}
