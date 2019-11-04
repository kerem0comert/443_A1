package com.keremcomert.javalib;

import java.util.ArrayList;

public class NonStudent extends Trainee implements Performance{
    private String job, position;
    private ArrayList<Course> enrolledList;
    private static final Float COEFFICENT = 0.4f;
    NonStudent(){}

    /**T
     * The constructor for NonStudent takes all the necessary arguments.
     * All params not specific to the student are sent to their corresponding
     * setters in the parent class.
     * @param name
     * @param eMail
     * @param password
     * @param gender
     * @param age
     * @param enrolledList - Even though abstract Person class has this as well, this has to
     *                     be specific to the user since User implements the performance method
     *                     while Person does not. And in the Performance method, enrolledList
     *                     is used.
     * @param premium
     * @param job - Specific to the non-student
     * @param position - Specific to the non-student
     */
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


    /**
     * This method is overridden from the Performance interface. It is calculated specifically
     * for the non-student.
     * @return Float for monthlyFee() that calls this method.
     */
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
