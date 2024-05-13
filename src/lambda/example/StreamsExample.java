package lambda.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExample {

    public static void main(String[] args) {

        List<Person> personList = Arrays.asList(
                new Person("shubham", "Chouksey", 23),
                new Person("Naman", "Chouksey", 9),
                new Person("Peepee", "Chouksey", 3),
                new Person("Sarita", "Chouksey", 30),
                new Person("Riyansh", "Jaiswal", 12),
                new Person("Ridhima", "Jaiswal", 12),
                new Person("Rahul", "Jaiswal", 33)
        );

        personList.stream().
                filter((p) -> p.getLastName().startsWith("C")).
                forEach(System.out::println);

        long count = personList.stream().
                filter((p) -> p.getLastName().startsWith("C")).count();
        System.out.println("count : " + count);

        long countUsingParallelStream = personList.parallelStream().
                filter((p) -> p.getLastName().startsWith("C")).count();
        System.out.println("countUsingParallelStream : " + countUsingParallelStream);


        List<Integer> numbers = Arrays.asList(1, 4, 5, 2, 6, 2, 7, 11, 4);

        numbers.stream().
                sorted(Comparator.reverseOrder()).
                distinct().
                forEach(number -> System.out.print(number + " "));


        System.out.println("\ncollecting the stream result ");
        List<Integer> l = numbers.stream().
                sorted(Comparator.reverseOrder()).
                distinct().
                collect(Collectors.toList());

        l.forEach(number -> System.out.print(number + " "));

    }


    public class MaxComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

}
