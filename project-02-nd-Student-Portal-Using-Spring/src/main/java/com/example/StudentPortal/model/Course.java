package com.example.StudentPortal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "course")
public class Course {

    @Id
    private String id;

    private String name;

    private String departmentId;   // reference

    private List<Semester> semesters; // embedded
}
