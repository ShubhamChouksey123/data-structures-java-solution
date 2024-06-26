package com.shubham.app.innerclass;

public class MainClass {

    public static void main(String[] ars) {
        nonStaticInnerClass();

        // staticInnerClass();

        // localInnerClass();
    }

    public static void nonStaticInnerClass() {
        OuterClassNonStaticInnerClass outerClass = new OuterClassNonStaticInnerClass();
        outerClass.greet();

        /** Non-Static-inner class also known as Member-Inner Class */
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

    public static void localInnerClass() {
        OuterClassLocalInnerClass outerClass = new OuterClassLocalInnerClass();
        outerClass.greet();

        /** local-inner class also known as Method-Local-Inner Class */
        /** Error : not possible, only accessible inside the method it is declared */
        // OuterClassLocalInnerClass.InnerClass innerClass = outerClass.new
        // OuterClassLocalInnerClass.InnerClass();
        // innerClass.innerGreet();
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
