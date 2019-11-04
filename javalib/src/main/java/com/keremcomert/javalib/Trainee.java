package com.keremcomert.javalib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Trainee extends Person {

    private ArrayList<Course> enrolledList;
    private Boolean premium;

    Trainee(){}

    Trainee(String name, String eMail, String password, char gender, int age,
            ArrayList<Course> enrolledList, Boolean premium){
        setName(name);
        seteMail(eMail);
        setPassword(password);
        setGender(gender);
        setAge(age);
        this.enrolledList = enrolledList;
        this.premium = premium;
    }

    public ArrayList<Course> getEnrolledList() { return enrolledList; }
    public void setEnrolledList(ArrayList<Course> enrolledList) { this.enrolledList = enrolledList; }

    public Boolean getPremium() { return premium; }
    public void setPremium(Boolean premium) { this.premium = premium; }

}
