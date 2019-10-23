package com.keremcomert.javalib;

public class Instructor extends Person {
    private int id;

    Instructor(String name, String eMail, String password, char gender, int age)
    {
        setName(name);
        seteMail(eMail);
        setPassword(password);
        setGender(gender);
        setAge(age);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

}
