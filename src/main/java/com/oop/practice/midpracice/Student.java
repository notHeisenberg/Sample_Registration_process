package com.oop.practice.midpracice;

import java.util.ArrayList;

public class Student {
    private Integer studentID;
    private Boolean hasScholarship;
    private Integer scholarshipRate;

    private ArrayList<RegisteredCourse> registeredCourseList = new ArrayList<>();

    public Student(Integer studentID) {
        this.studentID = studentID;
    }

    public Student(Integer studentID, Boolean hasScholarship, Integer scholarshipRate) {
        this.studentID = studentID;
        this.hasScholarship = hasScholarship;
        this.scholarshipRate = scholarshipRate;
        this.registeredCourseList = new ArrayList<>();
    }

    public RegisteredCourse registerCourse(String courseID, Integer courseCredit, Integer section) {
        if (courseID == null || courseCredit == null || section == null) {
            return null;
        }
        RegisteredCourse newRegisterCourse = new RegisteredCourse(courseID, courseCredit, section);
        registeredCourseList.add(newRegisterCourse);
        return newRegisterCourse;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Boolean getHasScholarship() {
        return hasScholarship;
    }

    public void setHasScholarship(Boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }

    public Integer getScholarshipRate() {
        return scholarshipRate;
    }

    public void setScholarshipRate(Integer scholarshipRate) {
        this.scholarshipRate = scholarshipRate;
    }

    @Override
    public String toString() {
        return "Student ID :" + studentID + ", Scholarship :" + hasScholarship + "," + scholarshipRate + " % " +
                "Tuition fee per credit: 6000\n\n";
    }
}
