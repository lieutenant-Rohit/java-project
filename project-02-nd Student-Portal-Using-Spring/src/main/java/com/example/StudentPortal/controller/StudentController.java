package com.example.StudentPortal.controller;

import com.example.StudentPortal.dto.request.StudentRequestDTO;
import com.example.StudentPortal.dto.response.StudentFullResponseDTO;
import com.example.StudentPortal.dto.response.StudentResponseDTO;
import com.example.StudentPortal.model.Student;
import com.example.StudentPortal.service.StudentAggregationService;
import com.example.StudentPortal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentAggregationService aggregationService;

    // 1️⃣ CREATE
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(studentService.create(dto));
    }

    // 2️⃣ GET ALL
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    // 3️⃣ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    // 4️⃣ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(
            @PathVariable String id,
            @RequestBody StudentRequestDTO dto
    ) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    // 5️⃣ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 6️⃣ STUDENT + COURSE DETAILS
    @GetMapping("/{id}/details")
    public ResponseEntity<StudentResponseDTO> getDetails(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentWithCourse(id));
    }

    @GetMapping("/{id}/full-details")
    public ResponseEntity<StudentFullResponseDTO> getFullDetails(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(
                aggregationService.getStudentWithCourseAndDepartment(id)
        );
    }

}