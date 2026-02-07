package com.example.StudentPortal.controller;

import com.example.StudentPortal.dto.request.CourseRequestDTO;
import com.example.StudentPortal.model.Course;
import com.example.StudentPortal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // CREATE
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDTO dto) {
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String id,
            @RequestBody CourseRequestDTO dto
    ) {
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}