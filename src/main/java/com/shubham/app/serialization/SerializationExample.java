package com.shubham.app.serialization;

import java.io.*;
import java.util.UUID;

public class SerializationExample {

    private static void example() throws IOException {

        User user = new User(UUID.randomUUID(), "Shubham", "Chouksey", 25);

        FileOutputStream fileOutputStream = new FileOutputStream("users");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private static void exampleDeserialization() throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("users");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User user = (User) objectInputStream.readObject();
        System.out.println("we have got a user : " + user);
        objectInputStream.close();
        fileInputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        example();
        exampleDeserialization();
    }
}
