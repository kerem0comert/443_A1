package com.keremcomert.javalib;

import java.util.ArrayList;


public class Student extends Trainee implements Performance{
    private String institution;


    private ArrayList<Course> enrolledList;
    private static final Float COEFFICENT = 0.8f;

    /**
     * All parameters that are not specific to this class are sent to the parent class as
     * setters.
     * @param name
     * @param eMail
     * @param password
     * @param gender
     * @param age
     * @param enrolledList        Even though abstract Person class has this as well, this has to
     *      *                     be specific to the user since User implements the performance method
     *      *                     while Person does not. And in the Performance method, enrolledList
     *      *                     is used.
     * @param premium -- Specific to Student
     * @param institution --Specific to student.
     */
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

    /**
     *
     * @return Float for monthlyFee() that calls this method.
     */
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

    /**
     * The calculations for this are same as the NonStudent apart from the times 2 that is not
     * present here.
     * @return Float is returned for the OnlineLearningApplication
     */
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
