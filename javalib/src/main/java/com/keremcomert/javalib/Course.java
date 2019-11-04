package com.keremcomert.javalib;

/**
 * All the regular getters and setters are implemented for the Course class.
 */
public class Course {

    private Instructor instructor;
    private String courseName;
    private Float duration;
    private Boolean premium;

    public Course(){}

    public Course(Instructor instructor, String courseName, Float duration, Boolean premium)
    {
        this.instructor = instructor;
        this.courseName = courseName;
        this.duration = duration;
        this.premium = premium;
    }


    public Instructor getInstructor() { return this.instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public String getName() { return this.courseName; }
    public void setName(String courseName) { this.courseName = courseName; }

    public Float getDuration() { return this.duration; }
    public void setDuration(Float duration) { this.duration = duration; }

    public Boolean getPremium() { return this.premium; }
    public void setPremium(Boolean premium) { this.premium = premium; }
}
