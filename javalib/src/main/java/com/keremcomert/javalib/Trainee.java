package com.keremcomert.javalib;

import java.util.List;

public class Trainee extends Person {

    private List<Course> enrolledList;
    private Boolean premium;

    Trainee(){}

    Trainee(String name, String eMail, String password, char gender, int age,
            List<Course> enrolledList, Boolean premium){
        setName(name);
        seteMail(eMail);
        setPassword(password);
        setGender(gender);
        setAge(age);
        this.enrolledList = enrolledList;
        this.premium = premium;
    }

    public List<Course> getEnrolledList() { return enrolledList; }
    public void setEnrolledList(List<Course> enrolledList) { this.enrolledList = enrolledList; }

    public Boolean getPremium() { return premium; }
    public void setPremium(Boolean premium) { this.premium = premium; }

}
