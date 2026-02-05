package console;

import model.Student;
import service.StudentService;

import java.util.Scanner;

public class CLI {

    private final Scanner scanner;
    private final StudentService service;

    public CLI(Scanner scanner, StudentService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void menu() {
        System.out.println("=== Student Management System ===");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. View Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> viewStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("Exiting... Bye ");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        Student student = new Student(name, course, department);
        int rollNo = service.addStudent(student);

        System.out.println("Student added successfully");
        System.out.println("Assigned Roll No: " + rollNo);
    }

    private void viewStudent() {
        System.out.print("Enter Roll No: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        Student student = service.viewStudent(rollNo);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    private void updateStudent() {
        System.out.print("Enter Roll No: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter New Department: ");
        String department = scanner.nextLine();

        Student updatedStudent = new Student(name, course, department);
        updatedStudent.setRollNo(rollNo);

        boolean updated = service.updateStudent(updatedStudent);

        if (updated) {
            System.out.println("student updated successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter Roll No: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = service.deleteStudent(rollNo);

        if (deleted) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }
}