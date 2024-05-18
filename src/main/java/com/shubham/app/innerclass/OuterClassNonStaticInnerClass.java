package com.shubham.app.innerclass;

public class OuterClassNonStaticInnerClass {

    private int age;
    private String name;

    public void greet() {
        System.out.println("Hello from the outer class");
    }

    /** Non Static inner class */
    public class InnerClass {

        private int innerId;

        public void innerGreet() {
            System.out.println("Hello from the inner class");
        }
    }
}
