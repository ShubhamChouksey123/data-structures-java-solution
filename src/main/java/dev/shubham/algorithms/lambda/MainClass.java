package dev.shubham.algorithms.lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;

public class MainClass {

    public static void main(String[] args) {

        Greeter greeter = new Greeter();
        greeter.greet();

        Greeter greeter2 = new Greeter();
        Greeting helloWorldGreeting = new GreetingImpl();
        greeter.greet(helloWorldGreeting);

        Runnable hel = () -> {
            System.out.println("Hello shubham 01");
        };

        greeter.greet(() -> {
            System.out.println("Hello shubham 02");
        });

        IntFunction<Integer> twiceNumberLambda = (int a) -> (2 * a);

        IntBinaryOperator sumLambda = (int a, int b) -> (a + b);

        IntBinaryOperator divisionLambda = (int a, int b) -> {
            if (b != 0) {
                return a / b;
            }
            return 0;
        };

        Function<String, Integer> stringLengthLambda = (String s) -> (s.length());

        /** The signature is must be same as of the functional interface */
        Greeting greeting = () -> System.out.println("Hello PP");

        SumInterface myLambda = (int a, int b) -> (a + b);

        /** here we are creating an actual implementation class of the Greeting */
        Greeting helloWorldGreeting1 = new GreetingImpl();
        Greeting lambdaGreeting = () -> System.out.println("Hello world greeting from inline lambda function  !");

        /**
         * here we are only creating the implementation of the function alone This is an
         * lambda functions of type Greeting interface
         */
        helloWorldGreeting1.perform();
        lambdaGreeting.perform();

        /**
         * this is an anonymous inner class Here we are creating the implementation of
         * Greeting interface
         */
        Greeting greetingAnonymousImplClass = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello world greeting from anonymous inner class !");
            }
        };
        greetingAnonymousImplClass.perform();
    }
}
