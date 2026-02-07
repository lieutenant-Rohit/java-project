package com.example.StudentPortal.dto.response;

import lombok.Data;

@Data
public class StudentResponseDTO {

    private String id;
    private String name;
    private String email;
    private int age;

    private Object course; // mapped from aggregation
}