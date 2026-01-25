package dev.shubham.algorithms.lambda;

public class RunnableExample {

    public static void main(String[] args) {

        /** Method 1 for creating a new thread and run using anonymous inner class */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World printed inside the run");
            }
        });

        thread.run();

        /** Method 2 for creating a new thread and run using lambda function */
        Thread thread2 = new Thread(() -> System.out.println("Hello World printed inside the lambda function"));
        thread2.start();
    }
}
