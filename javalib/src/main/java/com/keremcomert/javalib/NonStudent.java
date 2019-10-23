package com.keremcomert.javalib;

public class NonStudent extends Trainee implements Performance{
    private String job, position;

    NonStudent(String job, String position){
        this.job = job;
        this.position = position;
    }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public Float credit() {
        return null;
    }

    @Override
    public Float monthlyFee() {
        return null;
    }
}
