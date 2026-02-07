package com.example.StudentPortal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class Student {

    @Id
    private String id;

    private String name;
    private String email;
    private int age;

    // New Field to track which semester the student is in
    private int currentSemester;

    private String courseId; // reference to Course
}