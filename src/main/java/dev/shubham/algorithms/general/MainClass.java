package dev.shubham.algorithms.general;

public class MainClass {

    private int age;
    public String name = "shubham";

    public static void staticMethod() {
        System.out.println("Hey there from static method");
        /**
         * compilation error, we can't access non-static members variables from static
         * method
         */
        // System.out.println("Hey there" + name);
    }

    public void nonStaticMethod() {
        System.out.println("Hey there from non-static method");
        System.out.println("Hey there" + name);
    }
}
