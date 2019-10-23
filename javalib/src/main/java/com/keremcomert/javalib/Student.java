package com.keremcomert.javalib;

public class Student extends Trainee implements Performance{
    private String institution;

    Student(String institution){ this.institution = institution; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    @Override
    public Float credit() {
        return null;
    }

    @Override
    public Float monthlyFee() {
        return null;
    }
}
