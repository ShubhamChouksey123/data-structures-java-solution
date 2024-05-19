package com.shubham.app.innerclass;

public class OuterClassLocalInnerClass {

    public static final int DAYS_IN_WEEK = 7;
    public String address = "address";
    private int age = 2; // Must be final or effectively final to be accessed by inner class
    private String name;

    public void greet() {
        System.out.println("Hello from the outer class");

        /** these are known as Method Local Inner Class or Local Inner Class */
        age = 18;

        class InnerClass {
            public static final int CONSTANT_AGE = 21;
            private static int staticVariable = 102;
            int innerAge = 4;

            public void greetInner() {
                System.out.println("Hello from the local inner class");
                System.out.println("Hello from the local inner class  with outer age : " + age);
                System.out.println("Hello from the local inner class  with inner age : " + innerAge);
                System.out.println("Hello from the local inner class  with inner CONSTANT_AGE : " + CONSTANT_AGE);
                age = 30;
                System.out.println("Hello from the local inner class  with outer age : " + age);
                System.out.println("Hello from the local inner class  with outer staticVariable : " + staticVariable);
                greet2();
            }

            public static void greet2() {
                System.out.println("Hello from the static method inside local inner class");
            }
        }

        InnerClass innerClass = new InnerClass();
        innerClass.greetInner();
    }
}
