package com.shubham.app.lambda.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercise1 {

    private static final Logger logger = LoggerFactory.getLogger(Exercise1.class);

    public static void function() {

        List<Person> personList = Arrays.asList(new Person("shubham", "Chouksey", 23),
                new Person("Naman", "Chouksey", 9), new Person("Peepee", "Chouksey", 3),
                new Person("Sarita", "Chouksey", 30), new Person("Riyansh", "Jaiswal", 12),
                new Person("Ridhima", "Jaiswal", 12), new Person("Rahul", "Jaiswal", 33));

        /** Step 1 sort list by last name */
        Collections.sort(personList);

        /** Step 2 create a method that prints all elements in the list */
        printAllPersons(personList);
        /** or create a method that prints all elements in the list */
        personList.forEach(person -> logger.info("person for each : {}", person));

        personList.stream().forEach(person -> System.out.println("stream person : " + person));

        /** Step 3 print all persons with last name starting with C using simple loop */
        printAllPersonsWithCAsStartInLastName(personList);
        printConditionally(personList, new Condition() {
            @Override
            public boolean test(Person person) {
                return person.getLastName().startsWith("C");
            }
        });

        logger.info("Printing again using conditional interface implementation using lambda function");
        printConditionally(personList, (person) -> person.getLastName().startsWith("C"));

        /** Step 3 print all persons with last name starting with C using simple loop */
        personList.forEach(person -> {
            if (person.getLastName().charAt(0) == 'C') {
                System.out.println("forEach person : " + person);
            }
        });

        /** Step 3 print all persons with last name starting with C using streams */
        personList.stream().filter(person -> {
            return (person.getLastName().charAt(0) == 'C');
        }).forEach(person -> System.out.println("stream person : " + person));
    }

    public static void main(String[] args) {

        function();
    }

    private static void printAllPersons(List<Person> personList) {
        for (Person person : personList) {
            System.out.println("loop person : " + person);
        }
    }

    private static void printAllPersonsWithCAsStartInLastName(List<Person> personList) {
        for (Person person : personList) {
            if (person.getLastName().startsWith("C")) {
                System.out.println("loop person : " + person);
            }
        }
    }

    private static void printConditionally(List<Person> personList, Condition condition) {
        for (Person person : personList) {
            if (condition.test(person)) {
                System.out.println("condition loop person : " + person);
            }
        }
    }

    public interface Condition {
        boolean test(Person person);
    }
}
