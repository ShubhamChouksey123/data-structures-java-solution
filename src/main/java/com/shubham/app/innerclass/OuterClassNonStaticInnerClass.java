package com.shubham.app.innerclass;

public class OuterClassNonStaticInnerClass {

    public static final int DAYS_IN_WEEK = 7;
    private int age = 2;
    private String name;
    String address = "address";

    public void greet() {
        System.out.println("Hello from the outer class");
    }

    /** Non Static inner class */
    public class InnerClass {

        private static int DAYS_IN_MONTH = 30;
        private int innerId;

        public void innerGreet() {
            System.out.println("Hello from the public not static inner class : " + DAYS_IN_WEEK);
            System.out.println("Public not static inner class, and accessing static variable : " + DAYS_IN_MONTH);
            greet();
            System.out.println("address : " + address);
            address = "newAddress";
            System.out.println("address : " + address);
        }
    }
}
