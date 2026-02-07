package com.example.StudentPortal.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CourseRequestDTO {
    private String name;
    private String departmentId;
    private List<SemesterDTO> semesters;
}