package com.shubham.app.innerclass.anonymous;

public class MainClass {

    public static void main(String[] args) {
        /** Examples of anonymous inner class that implements an interface */
        AnonymousInterfaceImplClass anonymousInterfaceImplClass = new AnonymousInterfaceImplClass();
        anonymousInterfaceImplClass.implementInterface();

        /** Examples of anonymous inner class that extends a subclass */
        Animal bigAnimal = new Animal() {

            private static int age = 200;

            @Override
            public void sound() {
                System.out.println("Gheeehhee age " + age);
            }
        };

        bigAnimal.sound();
    }
}
