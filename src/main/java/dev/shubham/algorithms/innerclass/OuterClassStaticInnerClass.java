package dev.shubham.algorithms.innerclass;

public class OuterClassStaticInnerClass {

    public static int DAYS_IN_WEEK = 7;
    private int age = 2;
    private String name;

    public void greet() {
        System.out.println("Hello from the outer class");
    }

    /** Static inner class */
    public static class InnerClass {

        private int innerId;

        public void innerGreet() {

            System.out.println("Hello from the public static inner class");
            System.out.println("Hello from the public static inner class " + DAYS_IN_WEEK);
            /** error can't access member variable to outer class from inner class */
            // System.out.println("Hello from the public static inner class " + this.age);
            // System.out.println("Hello from the public static inner class " + greet());
        }
    }
}
