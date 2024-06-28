package com.oop.practice.midpracice;

public class RegisteredCourse {
    private String courseID;
    private Integer section, courseCredit,creditFee, totalFee;

    public RegisteredCourse(String courseID, Integer courseCredit, Integer section) {
        this.courseID = courseID;
        this.courseCredit = courseCredit;
        this.section = section;
        this.creditFee = 6000;
        this.totalFee = courseCredit * creditFee;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public String getCourseType() {
        String courseType;
        if (courseID.endsWith("L")) {
            courseType="Lab";
        }
        else{
            courseType="Theory";
        }
        return courseType;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Credit: " + courseCredit + ", Section: " + section + "\n";
    }
}
