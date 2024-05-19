package com.shubham.app.innerclass.anonymous;

import java.util.function.Consumer;

public class AnonymousInterfaceImplClass {

    /** Examples of anonymous inner class that implements an interface */
    public void implementInterface() {

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    System.out.println("the value of number is " + 1);
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    System.out.println("the value of number is " + 2);
                }
            }
        };

        // thread1.start();
        // thread2.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    System.out.println("the value of number is " + 3);
                }
            }
        };
        // runnable.run();

        method(5, (p) -> {
            System.out.println("the value of number is " + p);
        });

        method(5, new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("the value of number is " + integer);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("integer value is : " + integer);
            }
        };

        consumer.accept(42);
    }

    private void method(Integer number, Consumer<Integer> consumer) {
        consumer.accept(number);
    }
}
