package com.example.StudentPortal.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class SemesterDTO {
    private int semesterNumber;
    private List<SubjectDTO> subjects;
}
