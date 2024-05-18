package com.shubham.app.innerclass;

public class OuterClassStaticInnerClass {

    private int age;
    private String name;

    public void greet() {
        System.out.println("Hello from the outer class");
    }

    /** Static inner class */
    public static class InnerClass {

        private int innerId;

        public void innerGreet() {
            System.out.println("Hello from the inner class");
        }
    }
}
