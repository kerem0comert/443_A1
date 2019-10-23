package com.keremcomert.javalib;

import java.util.ArrayList;
import java.util.Scanner;

public class OnlineLearningApplication {
    static public Scanner in = new Scanner(System.in);
    private static ArrayList<Instructor> instructorList;
    private static ArrayList<Course> allCoursesList;
    private static Trainee trainee;

    public static void main(String[] args){ //assume that main = menu
        boolean isLoggedIn = false;
        createInstructorList();
        createCoursesList();

        System.out.println("Welcome to the Online Learning Application!\n");

        int option = 0;
        while(option != -1){
          System.out.println(" 1) Sign Up\n 2) Login\n 3) List All Courses\n " +
                  "4) List Enrolled Courses\n 5) Add Course\n 6) Delete Course\n " +
                  "7) Get Instructor Details\n 8) Change To Premium\n 9) Logout\n" +
                  "-1) Exit\n Option: ");
          option = in.nextInt();
            switch(option){
                case(1):
                    trainee = signUp();
                    break;
                case(2):
                    if(isLoggedIn) {
                        System.out.println("You are already logged in as " + trainee.getName());
                        break;
                    }
                    if (login()) {
                        System.out.println("Login Successful");
                        isLoggedIn = true; }
                    else System.out.println("Invalid password!");
                    break;
                case(3):
                    listCourses();
                    break;
                case(4):
                    if(!isLoggedIn){
                        System.out.println("You have to login first to enroll in any courses!");
                        break;
                    }
                    listEnrolledCourses();
                    break;
                case(5):
                    addCourse();
            }
        }



    }



    private static void createInstructorList() {
        instructorList = new ArrayList<>();

        instructorList.add(new Instructor("Tyrion Lannister", "tyrion@casterlyrock.com",
                "ringTheBells99", 'M', 35));
        instructorList.add(new Instructor("Robert Baratheon", "robert@dragonstone.com",
                "bobbyB", 'M', 42));
        instructorList.add(new Instructor("Sansa Stark", "sansa@winterfell.com",
                "qeen12", 'F', 20));
        instructorList.add(new Instructor("Oberyn Martell", "oberyn@sunspear.com",
                "eyepopper", 'M', 32));
        instructorList.add(new Instructor("Daenerys Targaryen", "daenerys@slaversbay.com",
                "dragonsAreCool", 'F', 25));
        for(int i = 0; i < instructorList.size(); i++){
            instructorList.get(i).setId(i);
        }
    }

    private static void createCoursesList() {
        allCoursesList = new ArrayList<>();

        allCoursesList.add(new Course(instructorList.get(0),
                "Linear Algebra", 60f, false));
        allCoursesList.add(new Course(instructorList.get(1),
                "Art History", 60f, true));
        allCoursesList.add(new Course(instructorList.get(2),
                "Ancient Roman Law", 60f, false));
        allCoursesList.add(new Course(instructorList.get(3),
                "Latin Dances", 60f, true));
        allCoursesList.add(new Course(instructorList.get(0),
                "Database Managment", 60f, false));
    }



    private static Trainee signUp() {
        String name, eMail, password;
        char gender;
        int age;

        System.out.println("----SIGN UP----\n");
        System.out.println("Enter name: ");
        name = in.next();

        System.out.println("Enter eMail: ");
        eMail = in.next();

        System.out.println("Enter password: ");
        password = in.next();

        System.out.println("Enter gender: ");
        gender = in.next().charAt(0);

        System.out.println("Enter age: ");
        age = in.nextInt();

        if(name == null || eMail == null || password == null){
            //not logical to check if char and int are null
            System.out.println("Invalid user creation. Try again!");
            return null; }

        System.out.println("User created with name: " + name);
        return new Trainee(name, eMail, password, gender, age, null, false);
    }

    private static String eMailFromUser() {
        return in.nextLine();
        /*if(!Patterns*/
    }

    private static Boolean login() {
        if(trainee == null) {
            System.out.println("You need to sign up first!");
            trainee = signUp();
        }
        System.out.println("Enter password to login: ");
        return trainee.getPassword().equals(in.next()); }


    private static void listCourses() {
        String leftAlignFormat = "| %-4d | %-20s | %-20s | %-4f | %-9b|%n";
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
        System.out.format("| ID   | CourseName           |     Instructor       | Duration  + Premium  |%n");
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");

        for(int i = 0; i < allCoursesList.size(); i++){
            System.out.format(leftAlignFormat, i ,  allCoursesList.get(i).getName()
            , allCoursesList.get(i).getInstructor().getName(), allCoursesList.get(i).getDuration(),
            allCoursesList.get(i).getPremium());
        }
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
    }

    private static void listEnrolledCourses() {
        if(trainee.getEnrolledList() == null){
            System.out.println("You have not enrolled in any courses yet! Try adding some.");
            return;
        }

        String leftAlignFormat = "| %-4d | %-20s | %-20s | %-4f | %-9b|%n";
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
        System.out.format("| ID   | CourseName           |     Instructor       | Duration  + Premium  |%n");
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");

        for(int i = 0; i < allCoursesList.size(); i++){
            System.out.format(leftAlignFormat, i ,  trainee.getEnrolledList().get(i).getName()
                    , trainee.getEnrolledList().get(i).getInstructor().getName(),
                    trainee.getEnrolledList().get(i).getDuration(),
                    trainee.getEnrolledList().get(i).getPremium());
        }
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
    }

    private static void addCourse() {
    }

}
