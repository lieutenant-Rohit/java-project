package com.example.StudentPortal.dto.request;

import lombok.Data;

@Data
public class StudentRequestDTO {
    private String name;
    private String email;
    private int age;

    // New Field
    private int currentSemester;

    private String courseId;
}