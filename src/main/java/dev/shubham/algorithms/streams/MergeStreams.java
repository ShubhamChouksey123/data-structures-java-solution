package dev.shubham.algorithms.streams;

// import one.util.streamex.StreamEx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MergeStreams {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 20, 22, 24);
        List<Integer> list2 = Arrays.asList(10, 12, 13, 14, 15, 21);

        Stream<Integer> mergedStream = Stream.concat(list1.stream(), list2.stream());

        mergedStream.sorted().forEach((number) -> System.out.println("number : " + number));
    }

    private void mergeMultipleStreams() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 0, 2, 4);
        List<Integer> list2 = Arrays.asList(10, 12, 13, 14, 11, 11);
        List<Integer> list3 = Arrays.asList(33, 35, 31, 33, 34, 34);
        List<Integer> list4 = Arrays.asList(43, 45, 41, 43, 44, 44);

        // Stream<Integer> mergedStream = StreamEx.of(list1.stream());
        //
        // mergedStream.
        // sorted().
        // forEach((number) -> System.out.println("number : " + number));
    }
}
