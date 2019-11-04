package com.keremcomert.javalib;

abstract public class Person {
    private String name, eMail, password;
    private char gender;
    private int age;

    Person(){}


    Person(String name, String eMail, String password, char gender, int age){
        this.name = name;
        this.eMail = eMail;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String geteMail() { return eMail; }
    public void seteMail(String eMail) { this.eMail = eMail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }





}

