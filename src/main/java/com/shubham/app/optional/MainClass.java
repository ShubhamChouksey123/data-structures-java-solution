package com.shubham.app.optional;

import java.util.Optional;

public class MainClass {

    /**
     * Java Optional must be used as return type, when your method has a possibility
     * of returning null
     */
    public static void main(String[] args) {

        Cat cat = getCatByName("ridhima");
        // System.out.println("cat : " + cat.getAge());

        initials();

        usingOptionals();
    }

    public static void initials() {

        Cat cat = getCatByName("ridhima");
        if (cat != null) {
            System.out.println("cat age : " + cat.getAge());
        } else {
            System.out.println("cat age : " + 0);
        }
    }

    private static Cat getCatByName(String name) {
        Cat cat = new Cat("ridhima", 3);
        return null;
    }

    public static void usingOptionals() {

        Optional<Cat> optionalCat = getOptionalCatByName("ridhima");
        if (optionalCat.isPresent()) {
            System.out.println("cat age : " + optionalCat.get().getAge());
        } else {
            System.out.println("cat age : " + 0);
        }

        Cat myCat1 = optionalCat.orElse(new Cat("UNKNOWN", 0));
        System.out.println("myCat1 : " + myCat1);

        Cat myCat2 = optionalCat.orElseGet(() -> new Cat("UNKNOWN", 0));
        System.out.println("myCat1 : " + myCat1);

        Integer catAge = optionalCat.map((cat) -> cat.getAge()).orElse(0);

        System.out.println("catAge : " + catAge);
    }

    private static Optional<Cat> getOptionalCatByName(String name) {
        Cat cat = new Cat("ridhima", 3);
        return Optional.ofNullable(null);
    }
}
