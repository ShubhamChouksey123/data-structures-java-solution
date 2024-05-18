package com.shubham.app.lambda.exception;

import java.util.function.BiConsumer;

public class ExceptionHandling {


    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4};
        int key = 0;

        /**
         *
         */
        process(numbers, key, (v, k) -> System.out.println(v + k));


        process(numbers, key, (v, k) -> {

            try {
                System.out.println(v / k);
            } catch (Exception e) {
                System.out.println("we have an arithmetic exception when using a inline try catch");
            }

        });

        process(numbers, key, wrapperLambda((v, k) -> {System.out.println(v / k);}));

    }

    private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
        return (v, k) -> {
            try {
                System.out.println(v / k);
            } catch (Exception e) {
                System.out.println("we have an arithmetic exception when using a wrapper lambda function");
            }
        };
    }

    private static void process(int[] numbers, int key, BiConsumer<Integer, Integer> consumer) {
        for (int number : numbers) {
            consumer.accept(number, key);
        }
    }
}
