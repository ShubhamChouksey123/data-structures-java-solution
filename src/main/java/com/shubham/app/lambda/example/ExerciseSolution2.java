package com.shubham.app.lambda.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ExerciseSolution2 {


    private static final Logger logger = LoggerFactory.getLogger(ExerciseSolution2.class);

    public static void function() {

        List<Person> personList = Arrays.asList(new Person("shubham", "Chouksey", 23),
                new Person("Naman", "Chouksey", 9), new Person("Peepee", "Chouksey", 3),
                new Person("Sarita", "Chouksey", 30), new Person("Riyansh", "Jaiswal", 12),
                new Person("Ridhima", "Jaiswal", 12), new Person("Rahul", "Jaiswal", 33));

        /** Step 1 sort list by last name */
        personList.sort((Person p1, Person p2) -> {
            if (Objects.equals(p1.getLastName(), p2.getLastName())) {
                return p1.getFirstName().compareTo(p2.getFirstName());
            }

            return p1.getLastName().compareTo(p2.getLastName());
        });

        /** Step 2 create a method that prints all elements in the list */
        printConditionally(personList, (person) -> true);


        /** Step 3 print all persons with last name starting with C using simple loop */

        logger.info("Printing again using conditional interface implementation using lambda function");
        /** Step 3 print all persons with last name starting with C using simple loop */
        printConditionally(personList, (person) -> person.getLastName().charAt(0) == 'C');
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

    private static void printConditionally(List<Person> personList, Predicate<Person> predicate) {
        for (Person person : personList) {
            if (predicate.test(person)) {
                System.out.println("condition loop person : " + person);
            }
        }
    }

    public interface Condition {
        boolean test(Person person);
    }


}

