package com.example.StudentPortal.dto.response;

import lombok.Data;

@Data
public class StudentFullResponseDTO {

    private String id;
    private String name;
    private String email;
    private int age;

    // ✅ NEW FIELD: Required for the frontend to show "Semester 1" instead of "?"
    private int currentSemester;

    private CourseWithDepartmentDTO course;
}