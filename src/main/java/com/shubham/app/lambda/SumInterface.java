package com.shubham.app.lambda;

@FunctionalInterface
public interface SumInterface {
    /**
     * an interface which has exactly one abstract method is called functional
     * interface
     * <li>Adding @FunctionalInterface makes this interface to have exactly one
     * abstract method
     * <li>Java Util commonly used function interface can be found here : <a href=
     * "https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">...</a>
     *
     * @param a
     * @param b
     * @return
     */
    int sum(int a, int b);
}
