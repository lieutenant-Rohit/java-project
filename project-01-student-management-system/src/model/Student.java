package model;

public class Student {

    private int rollNo;
    private String name;
    private String course;
    private String department;

    public Student(String name, String course, String department) {
        this.name = name;
        this.course = course;
        this.department = department;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "\nStudent Details" +
                "\n-----------------" +
                "\nRoll No    : " + rollNo +
                "\nName       : " + name +
                "\nCourse     : " + course +
                "\nDepartment : " + department;
    }
}