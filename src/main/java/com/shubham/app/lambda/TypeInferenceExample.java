package com.shubham.app.lambda;

/** calling a lambda function */
public class TypeInferenceExample {

    public static void main(String[] args) {

        StringLengthLambda myStringLengthLambda = (s) -> s.length();
        /** Method 1 this is how we call a lambda function */
        System.out.println(myStringLengthLambda.getLength("Shubham Chouksey"));

        /** Method 2 this is how we call a lambda function */
        printLambda(s -> s.length());
    }

    public static void printLambda(StringLengthLambda l) {
        System.out.println(l.getLength("Shubham Chouksey"));
    }

    public interface StringLengthLambda {
        int getLength(String s);
    }
}
