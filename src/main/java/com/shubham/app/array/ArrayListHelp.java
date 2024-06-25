package com.shubham.app.array;

import java.util.*;

public class ArrayListHelp {


    public static void main(String[] args) {


        List<Integer> list1 = new ArrayList<>();
        System.out.println("list1 : " + list1);

        List<Integer> list2 = new ArrayList<>(3);
        System.out.println("list2 : " + list2);


        List<Integer> list3 = List.of(1, 2, 3);
        System.out.println("list3 : " + list3);

        List<Integer> list4 = Arrays.asList(1, 2);
        System.out.println("list4 : " + list4);

        List<Integer> list5 = Arrays.asList(new Integer[10]);
        System.out.println("list5 : " + list5);
     }
}
