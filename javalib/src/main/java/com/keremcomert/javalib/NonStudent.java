package com.keremcomert.javalib;

import java.util.ArrayList;

public class NonStudent extends Trainee implements Performance{
    private String job, position;
    private ArrayList<Course> enrolledList;
    private static final Float COEFFICENT = 0.4f;
    NonStudent(){}

    public NonStudent(String name, String eMail, String password, char gender, int age,
                       ArrayList<Course> enrolledList, Boolean premium, String job, String position) {
        setName(name);
        seteMail(eMail);
        setPassword(password);
        setGender(gender);
        setPremium(premium);
        setAge(age);
        this.enrolledList = enrolledList;
        this.job = job;
        this.position = position;
    }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public Float credit() {
        Float premiumCourseCount = 0f;
        for(int i = 0; i < enrolledList.size(); i++ ){
            if(enrolledList.get(i).getPremium())
                System.out.println("Premium!");
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

        return (10 * premiumCourseCount - credit()) * 2;
    }

    //these need to be overridden from the parent to calculate fee correctly
    @Override
    public ArrayList<Course> getEnrolledList() { return enrolledList; }

    @Override
    public void setEnrolledList(ArrayList<Course> enrolledList) { this.enrolledList = enrolledList; }
}
