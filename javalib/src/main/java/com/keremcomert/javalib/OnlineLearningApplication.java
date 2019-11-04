package com.keremcomert.javalib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;



public class OnlineLearningApplication {
    static public Scanner in = new Scanner(System.in);
    private static ArrayList<Instructor> instructorList;
    private static ArrayList<Course> allCoursesList;
    private static ArrayList<Trainee> traineeList;
    private static Trainee trainee;

    /**
     *
     * @param args command line arguments are not used
     */
    public static void main(String[] args) { //assume that main = menu
        boolean isLoggedIn = false;
        createInstructorList();
        createCoursesList();
        traineeList = new ArrayList<>();
        System.out.println("Welcome to the Online Learning Application!\n");

        int option = 0;
        while (option != -1) {
            System.out.println(" 1) Sign Up\n 2) Login\n 3) List All Courses\n " +
                    "4) List Enrolled Courses\n 5) Add Course\n 6) Delete Course\n " +
                    "7) Get Instructor Details\n 8) Change To Premium\n 9) Credit\n" +
                    "10) Logout\n -1) Exit\n Option: ");

            option = in.nextInt();
            in.nextLine(); // to consume newline left-over
            switch (option) {
                case (1):
                    trainee = signUp();
                    traineeList.add(trainee);
                    break;
                case (2):
                    if (isLoggedIn) {
                        System.out.println("You are already logged in as " + trainee.getName());
                        break;
                    }
                    if (login()) {
                        System.out.println("Login Successful");
                        isLoggedIn = true;
                        trainee = changeToPremium();
                    } else System.out.println("Invalid password!");
                    break;
                case (3):
                    if(!isLoggedIn){
                        System.out.println("Login first to see the courses list!");
                    }
                    listCourses();
                    break;
                case (4):
                    if (!isLoggedIn) {
                        System.out.println("You can't have any courses enrolled if you are not logged in!");
                        break;
                    }
                    listEnrolledCourses();
                    break;
                case (5):
                    if (!isLoggedIn) {
                        System.out.println("Login or sign up first to enroll in any course.");
                        break;
                    }
                    addCourse();
                    break;
                case(6):
                    if(!isLoggedIn){
                        System.out.println("Login or sign up first to delete a course from your curriculum");
                        break;
                    }
                    deleteCourse();
                    break;
                case(7):
                    if(!isLoggedIn){
                        System.out.println("Login or sign up first to see instructor details.");
                        break;
                    }
                    getInstructorDetails();
                    break;
                case(8):
                    if(!isLoggedIn){
                        System.out.println("You can't change to premium without logging in first!");
                        break;
                    }
                    changeToPremium();
                    break;
                case(9):
                    if(trainee instanceof Student)
                        System.out.println("Fee: " + ((Student) trainee).monthlyFee() + "$");
                    else System.out.println("Fee: " + ((NonStudent) trainee).monthlyFee() + "$");

                    break;

                case(10):
                    trainee = null;
                    isLoggedIn = false;
                    break;
                case(-1):
                    exit();
                    break;
                default:
                    System.out.println("Invalid selection!");
                    break;
            }
        }


    }

    /**
     * creates an arrayList. Populates it with different instructor instances I've created
     */
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
        for (int i = 0; i < instructorList.size(); i++) {
            instructorList.get(i).setId(i);
        }
    }

    /**
     * Same as createInstructorList, the allCoursesList gets populated
     */
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
        allCoursesList.add(new Course(instructorList.get(4),
                "Database Managment", 60f, false));

        //assign courses to instructors
        for(int i = 0; i < allCoursesList.size(); i++){
            instructorList.get(i).setCourseOffered(allCoursesList.get(i));
        }
    }

    /**
     *
     * @return
     */
    private static Trainee signUp() {
        String name, eMail, password, institution, job, position;
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


        if (name == null || eMail == null || password == null) {
            //not logical to check if char and int are null
            System.out.println("Invalid user creation. Try again!");
            return null;
        }



        System.out.println("Are you a student or non-student? (S/N): ");
        char sOrN = in.next().charAt(0);
        if(sOrN == 'S'){
            System.out.println("You are a student.");
            System.out.println("Enter institution:");
            institution = in.next();
            if(institution == null) {
                System.out.println("Institution cannot be empty. Try again.");
                return null;
            }
            System.out.println("User created with name: " + name + " S/He is a student.");
            return new Student(name, eMail, password, gender, age, new ArrayList<Course>(), false,
                    institution);
        }
        else if(sOrN == 'N'){
            System.out.println("You are a non-student.");
            System.out.println("Enter your job: ");
            job = in.next();
            System.out.println("Enter your position: ");
            position = in.next();
            if(job == null || position == null){
                System.out.println("Both job and position should be filled. Try again.");
                return null;
            }
            System.out.println("User created with name: " + name + " S/He is a non-student.");
            return new NonStudent(name, eMail, password, gender, age, new ArrayList<Course>(), false,
                    job, position);
        }
        else{
            System.out.println("Invalid selection. Try again.");
            return null;

        }
    }

    private static String eMailFromUser() {
        return in.next();
        /*if(!Patterns*/
    }

    private static Boolean login() {
        if (trainee == null) {
            System.out.println("You need to sign up first!");
            trainee = signUp();
        }
        int position = -1;

        //see if such a user exists
        System.out.print("Enter e-mail to login: ");
        String eMailToCheck = in.next();
        for(int i = 0; i < traineeList.size(); i++){
            if(eMailToCheck.equals(traineeList.get(i).geteMail()))
                position = i;
        }

        if(position == -1){
            System.out.println("No such user exists with that eMail!");
            return false;
        }
        System.out.println("Email: " + traineeList.get(position).geteMail());
        System.out.println("Enter password to login: ");
        return traineeList.get(position).getPassword().equals(in.next());
    }


    private static void listCourses() {
        String leftAlignFormat = "| %-4d | %-20s | %-20s | %-4f | %-9b|%n";
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
        System.out.format("| ID   | CourseName           |     Instructor       | Duration  + Premium  |%n");
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");

        for (int i = 0; i < allCoursesList.size(); i++) {
            if(trainee.getPremium() || (!allCoursesList.get(i).getPremium()) )
                System.out.format(leftAlignFormat, i, allCoursesList.get(i).getName()
                    , allCoursesList.get(i).getInstructor().getName(), allCoursesList.get(i).getDuration(),
                    allCoursesList.get(i).getPremium());
        }
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
    }

    private static void listEnrolledCourses() {
        if (trainee.getEnrolledList() == null) {
            System.out.println("You have not enrolled in any courses yet! Try adding some.");
            return;
        }

        String leftAlignFormat = "| %-4d | %-20s | %-20s | %-4f | %-9b|%n";
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
        System.out.format("| ID   | CourseName           |     Instructor       | Duration  + Premium  |%n");
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");

        for (int i = 0; i < trainee.getEnrolledList().size() ; i++) {
            System.out.format(leftAlignFormat, i, trainee.getEnrolledList().get(i).getName()
                    , trainee.getEnrolledList().get(i).getInstructor().getName(),
                    trainee.getEnrolledList().get(i).getDuration(),
                    trainee.getEnrolledList().get(i).getPremium());
        }
        System.out.format("+------+----------------------+----------------------+-----------+----------|%n");
    }

    private static void addCourse() {
        listCourses();
        boolean courseFound = false;
        System.out.println("Enter the name of the course you want to enroll in: ");
        String enrolledCourse = in.nextLine();
        System.out.println(enrolledCourse + " enrolled");
        /*it would be more efficient to directly ask the ID int from the user, but since the
          assigment pdf wants me to parse all the names, I do as such*/
        for (int i = 0; i < allCoursesList.size(); i++) {
            if (enrolledCourse.equals(allCoursesList.get(i).getName())) {
                if(allCoursesList.get(i).getPremium() && (!trainee.getPremium()) ){
                    System.out.println("You have to be a premium member to take this course.");
                    break;
                }
                System.out.println(allCoursesList.get(i).getName() + "is added to your curriculum.");
                courseFound = true;
                if(trainee.getEnrolledList() == null){
                    ArrayList<Course> updatedList = new ArrayList<>();
                    updatedList.add(allCoursesList.get(i));
                    trainee.setEnrolledList(updatedList);
                    return;
                }
                ArrayList<Course> updatedList = trainee.getEnrolledList();
                updatedList.add(allCoursesList.get(i));
                trainee.setEnrolledList(updatedList);

            }
        }
        if (!courseFound) System.out.println("The course you seek is not offered by the platform.");
    }

    private static void deleteCourse() {
        if(trainee.getEnrolledList() == null || trainee.getEnrolledList().isEmpty()){
            System.out.println("You haven't enrolled in any courses yet. ");
            return;
        }
        listEnrolledCourses();
        System.out.println("Enter the name of the course you want to delete from your curriculum: ");
        String courseToBeDeleted = in.nextLine();
        boolean courseFound = false;
        for (int i = 0; i < trainee.getEnrolledList().size(); i++) {
            if (courseToBeDeleted.equals(trainee.getEnrolledList().get(i).getName())) {
                System.out.println(trainee.getEnrolledList().get(i).getName() + "is removed from your curriculum.");
                ArrayList<Course> updatedList = trainee.getEnrolledList();
                updatedList.remove(i);
                trainee.setEnrolledList(updatedList);
                courseFound = true;
                break;
            }
        }
        if (!courseFound) System.out.println("The course you seek is not in your curriculum.");
    }

    private static void getInstructorDetails() {

        String leftAlignFormat = "| %-4d | %-21s |%n";
        System.out.format("+------+-----------------------|%n");
        System.out.format("| ID   | Instructor Name       |%n");
        System.out.format("+------+-----------------------|%n");
        for(int i = 0; i < instructorList.size(); i++)
            System.out.format(leftAlignFormat, i, instructorList.get(i).getName());
        System.out.format("+------+-----------------------|%n");
        System.out.println("ID of the instructor that you want to see details of: ");

        int selectedId = in.nextInt();
        if(selectedId < 0 || selectedId >= instructorList.size()){
            System.out.println("That ID does not exist;");
            return;
        }

        Instructor instructorSelected = instructorList.get(selectedId);

        leftAlignFormat = "| %-4d | %-20s | %-20s | %-9c | %-8d | %-19s|%n";
        System.out.format("+------+----------------------+----------------------+-----------+----------" +
                "|--------------------|%n");
        System.out.format("| ID   | Instructor Name      |     Email            | Gender    +  Age     " +
                "|--------------------|%n");
        System.out.format("+------+----------------------+----------------------+-----------+----------" +
                "|--------------------|%n");

        System.out.format(leftAlignFormat, instructorSelected.getId(), instructorSelected.getName(),
        instructorSelected.geteMail(), instructorSelected.getGender(), instructorSelected.getAge(),
        instructorSelected.getCourseOffered().getName());

        System.out.format("+------+----------------------+----------------------+-----------+----------" +
                "|--------------------|%n");
    }

    private static Trainee changeToPremium() {
        if(trainee.getPremium()){
            System.out.println("You are a premium member");
            return trainee;
        }
        System.out.println("Would you like to switch to premium (Y/N)?");
        char selection = in.next().charAt(0);
        if(selection == 'Y'){
            trainee.setPremium(true);
            return trainee;
        }
        else if(selection == 'N'){
            System.out.println("You didn't switch to premium");
            return trainee;
        }
        else changeToPremium();
        return trainee;
    }

    private static void exit() {System.exit(0); }

}
