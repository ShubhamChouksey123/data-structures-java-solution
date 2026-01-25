package dev.shubham.algorithms.lambda;

public class Greeter {

    public static void greet() {
        System.out.println("Hello World");
    }

    public static void greet(Greeting greeting) {
        greeting.perform();
    }
}
