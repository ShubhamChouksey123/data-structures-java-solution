package com.shubham.app.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClass {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    private static void someStringOperations() {
        String s = "Hello world";
        logger.info("string length : " + s.length());
        logger.info("first char : " + s.charAt(0));


        String s2 = "  Hello world  ";
        logger.info("string length : " + s2.length());
        s2 = s2.trim();
        logger.info("string length : " + s2.length());

        logger.info("concat : " + s.concat(s2));
        logger.info("s : " + s);
        logger.info("s2 : " + s2);


        logger.info("contains ello : " + s.contains("ello"));
        logger.info("startsWith He : " + s.startsWith("He"));
        logger.info("startsWith llo : " + s.endsWith("llo"));
        logger.info("equals hello word : " + s.equals("hello world"));
        logger.info("equalsIgnoreCase hello word : " + s.equalsIgnoreCase("hello world"));
        logger.info("string indexOf : " + s.indexOf("l"));
        logger.info("string indexOf : " + s.indexOf("notpresent"));
        logger.info("string lastIndexOf : " + s.lastIndexOf("l"));

        logger.info("string isEmpty : " + s.isEmpty());
        logger.info("string isEmpty for zero length string : " + "".isEmpty());
        logger.info("string isEmpty for zero length string : " + "  ".isEmpty());


        logger.info("string to lower case : " + s.toLowerCase());
        logger.info("string to upper case : " + s.toUpperCase());

        byte[] bytes = s.getBytes();


    }

    private static void someToStringConversions() {

        String s = String.valueOf(1);
        s = String.valueOf("hello");
        s = String.valueOf(11.2);
        s = String.valueOf('c');

        s = String.valueOf(false);
        logger.info("s : {}", s);

        logger.info("s charAt : {}", s.charAt(1));
    }

    private static void someSubstring() {
        /**
         * s.substring(startIndex, endIndex)
         * endIndex will not be included the substring
         */
        String s = "Hello Shubham";
        logger.info("substring from 0 index till end : {}", s.substring(0, s.length()));
        logger.info("substring from index 0 to 2(excluded) : {}", s.substring(0, 2));
        logger.info("substring starting from index 2 till end : {}", s.substring(2));
    }

    public static void main(String[] args) {
        someStringOperations();
        someToStringConversions();

        someSubstring();
    }

}
