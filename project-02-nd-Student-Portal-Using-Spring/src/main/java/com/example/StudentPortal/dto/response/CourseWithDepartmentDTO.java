package com.example.StudentPortal.dto.response;

import lombok.Data;
import java.util.List;
import com.example.StudentPortal.model.Semester;

@Data
public class CourseWithDepartmentDTO {

    private String id;
    private String name;
    private DepartmentResponseDTO department;
    private List<Semester> semesters;
}