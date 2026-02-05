package service;

import model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> studentList = new ArrayList<>();
    private int rollCounter = 1;

    public int addStudent(Student student) {
        student.setRollNo(rollCounter);
        studentList.add(student);
        return rollCounter++;
    }

    public Student viewStudent(int rollNo) {
        for (Student student : studentList) {
            if (student.getRollNo() == rollNo) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudent(Student updatedStudent) {
        for (Student student : studentList) {
            if (student.getRollNo() == updatedStudent.getRollNo()) {
                student.setName(updatedStudent.getName());
                student.setCourse(updatedStudent.getCourse());
                student.setDepartment(updatedStudent.getDepartment());
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int rollNo) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNo() == rollNo) {
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }
}