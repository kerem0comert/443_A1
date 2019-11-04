package com.keremcomert.javalib;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.Console;
import java.util.ArrayList;

public class Student extends Trainee implements Performance{
    private String institution;


    private ArrayList<Course> enrolledList;
    private static final Float COEFFICENT = 0.8f;

    Student(String name, String eMail, String password, char gender, int age,
            ArrayList<Course> enrolledList, Boolean premium, String institution){
        setName(name);
        seteMail(eMail);
        setPassword(password);
        setGender(gender);
        setAge(age);
        setPremium(premium);
        this.institution = institution;
        this.enrolledList = enrolledList;
    }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    @Override
    public Float credit() {
        Float premiumCourseCount = 0f;
        System.out.println(enrolledList.size());
        for(int i = 0; i < enrolledList.size(); i++ ){
            if(enrolledList.get(i).getPremium())
                premiumCourseCount++;
        }

        return premiumCourseCount * COEFFICENT;
    }

    @Override
    public Float monthlyFee() {
        Float premiumCourseCount = 0f;
        for(int i = 0; i < enrolledList.size(); i++ ){
            if(enrolledList.get(i).getPremium())
                premiumCourseCount++;
        }

        return (10 * premiumCourseCount - credit()) ;
    }


    //these need to be overridden from the parent to calculate fee correctly
    @Override
    public ArrayList<Course> getEnrolledList() { return enrolledList; }

    @Override
    public void setEnrolledList(ArrayList<Course> enrolledList) { this.enrolledList = enrolledList; }
}
