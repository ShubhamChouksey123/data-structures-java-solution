package dev.shubham.algorithms.serialization;

import java.io.*;

public class ExternalizationExample {

    private static void example() throws IOException {

        Address address = new Address(101, "near big bazzar", "Vijay Nagar", "MP", "IN", "455123");

        FileOutputStream fileOutputStream = new FileOutputStream("users");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(address);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private static void exampleDeserialization() throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("users");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Address address = (Address) objectInputStream.readObject();
        System.out.println("we have got a address : " + address);
        objectInputStream.close();
        fileInputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        example();
        exampleDeserialization();
    }
}
