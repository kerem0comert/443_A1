package com.keremcomert.javalib;

import java.util.ArrayList;

/**
 * Nothing spectacular here apart from some getters and setters.
 */
public class Instructor extends Person {
    private int id;
    private Course courseOffered;

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

    public Course getCourseOffered() { return courseOffered; }
    public void setCourseOffered(Course courseOffered) { this.courseOffered = courseOffered; }

}
