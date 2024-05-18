package com.shubham.app.innerclass;

public class MainClass {

    public static void main(String[] ars) {
        OuterClassNonStaticInnerClass outerClass = new OuterClassNonStaticInnerClass();
        outerClass.greet();

        /** Non Static inner class */
        OuterClassNonStaticInnerClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.innerGreet();
    }

    public static void staticInnerClass() {
        OuterClassStaticInnerClass outerClass = new OuterClassStaticInnerClass();
        outerClass.greet();

        /** Static inner class */
        // OuterClassNonStaticInnerClass.InnerClass innerClass = outerClass.new
        // InnerClass();
        // innerClass.innerGreet();

    }
}
