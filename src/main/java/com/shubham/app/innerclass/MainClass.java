package com.shubham.app.innerclass;

public class MainClass {

    public static void main(String[] ars) {
        nonStaticInnerClass();

        // staticInnerClass();
    }

    public static void nonStaticInnerClass() {
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
        OuterClassStaticInnerClass.InnerClass innerClass = new OuterClassStaticInnerClass.InnerClass();
        innerClass.innerGreet();
    }

    public static void staticPrivateInnerClass() {
        OuterClassPrivateNonStaticInnerClass outerClass = new OuterClassPrivateNonStaticInnerClass();
        outerClass.greet();

        /** Static inner class Error : not possible */
        // OuterClassPrivateNonStaticInnerClass.InnerClass innerClass = outerClass.new
        // OuterClassPrivateNonStaticInnerClass.InnerClass();
        // innerClass.innerGreet();
    }
}
