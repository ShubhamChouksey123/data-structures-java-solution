package lambda.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercise1 {


    public static void function() {

        List<Person> personList = Arrays.asList(
                new Person("shubham", "Chouksey", 23),
                new Person("Naman", "Chouksey", 9),
                new Person("Peepee", "Chouksey", 3),
                new Person("Sarita", "Chouksey", 30),
                new Person("Riyansh", "Jaiswal", 12),
                new Person("Ridhima", "Jaiswal", 12),
                new Person("Rahul", "Jaiswal", 33)
        );


        /**
         * Step 1
         * sort list by last name
         */
        Collections.sort(personList);


        /**
         * Step 2
         * create a method that prints all elements in the list
         */
        for (Person person : personList) {
            System.out.println("loop person : " + person);
        }
        /**
         * or create a method that prints all elements in the list
         */
        personList.forEach(person -> System.out.println("lambda person : " + person));

        personList.stream().forEach(person -> System.out.println("stream person : " + person));

        /**
         * Step 3
         * print all persons with last name starting with C
         *  using simple loop
         */
        for (Person person : personList) {
            if (person.getLastName().charAt(0) == 'C') {
                System.out.println("loop person : " + person);
            }
        }


        /**
         * Step 3
         * print all persons with last name starting with C
         *  using simple loop
         */
        personList.forEach(person -> {
            if (person.getLastName().charAt(0) == 'C') {
                System.out.println("forEach person : " + person);
            }
        });



        /**
         * Step 3
         * print all persons with last name starting with C
         *  using streams
         */
        personList.stream().
                filter(person -> {
                    return (person.getLastName().charAt(0) == 'C');
                }).forEach(person -> System.out.println("stream person : " + person));



    }

    public static void main(String[] args) {

        function();


    }
}
