package dev.shubham.algorithms.lambda.example;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private Integer age;

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + '}';
    }

    @Override
    public int compareTo(Person o) {
        if (Objects.equals(this.lastName, o.lastName)) {
            return this.firstName.compareTo(o.getFirstName());
        }

        return this.lastName.compareTo(o.getLastName());
    }
}
