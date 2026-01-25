package dev.shubham.algorithms.general.staticppkg;

class Parent {
    static void print() {
        System.out.println("Hello From the parent class");
    }
}

class Child extends Parent {
    static void print() {
        System.out.println("Hello From the child class");
    }
}

public class MainClass {

    public static void main(String[] args) {
        Parent.print();
        Child.print();

        /** Hello From the parent class */
        Parent parent = new Parent();
        parent.print();

        /** Hello From the parent class */
        Parent parent1 = new Child();
        parent1.print();

        /** Hello From the child class* */
        Child child = new Child();
        child.print();
    }
}
