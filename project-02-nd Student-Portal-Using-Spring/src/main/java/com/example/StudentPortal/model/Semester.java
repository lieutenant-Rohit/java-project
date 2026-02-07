package com.example.StudentPortal.model;

import lombok.Data;
import java.util.List;

@Data
public class Semester {
    private int semesterNumber;
    private List<Subject> subjects;
}