package dev.shubham.algorithms.set;

import java.util.*;

public class Duplicates {

    private static void getDuplicates(List<Integer> numbers){
        Set<Integer> isFound = new HashSet<>();
        Set<Integer> output = new HashSet<>();


        for(Integer num : numbers){
            if(isFound.contains(num)){
                if(!output.contains(num)){
                    output.add(num);
                }
            }else {
                isFound.add(num);
            }
        }

        System.out.println("output : " + output);
    }

    public static void main(String[] args){
        List<Integer> numbers = List.of(5, 4, 5, 2, 3, 6, 3, 5, 5, 6, 1, 0);
        getDuplicates(numbers);
    }
}
