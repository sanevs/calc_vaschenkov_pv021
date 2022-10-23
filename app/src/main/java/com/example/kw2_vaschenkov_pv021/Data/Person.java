package com.example.kw2_vaschenkov_pv021.Data;

public class Person {
    String name;
    String speciality;
    int age;
    boolean gender;

    public Person(String name, String speciality, int age, boolean gender) {
        this.name = name;
        this.speciality = speciality;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
