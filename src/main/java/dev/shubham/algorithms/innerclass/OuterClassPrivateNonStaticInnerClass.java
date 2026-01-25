package dev.shubham.algorithms.innerclass;

public class OuterClassPrivateNonStaticInnerClass {

    private int age;
    private String name;

    public void greet() {
        System.out.println("Hello from the outer class");
    }

    /** Non Static inner class */
    private class InnerClass {

        private int innerId = 9;

        public void innerGreet() {
            System.out.println("Hello from the private non-static inner class with " + innerId);
        }
    }
}
