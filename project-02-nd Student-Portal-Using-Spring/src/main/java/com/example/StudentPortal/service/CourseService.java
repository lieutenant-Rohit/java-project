package com.example.StudentPortal.service;

import com.example.StudentPortal.dto.request.CourseRequestDTO;
import com.example.StudentPortal.dto.request.SemesterDTO;
import com.example.StudentPortal.dto.request.SubjectDTO;
import com.example.StudentPortal.model.Course;
import com.example.StudentPortal.model.Semester;
import com.example.StudentPortal.model.Subject;
import com.example.StudentPortal.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    // CREATE
    public Course createCourse(CourseRequestDTO dto) {

        Course course = new Course();
        course.setName(dto.getName());
        course.setDepartmentId(dto.getDepartmentId());

        List<Semester> semesters = dto.getSemesters()
                .stream()
                .map(this::mapSemester)
                .collect(Collectors.toList());

        course.setSemesters(semesters);

        return courseRepository.save(course);
    }

    // GET ALL
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // GET BY ID
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // UPDATE
    public Course updateCourse(String id, CourseRequestDTO dto) {
        Course course = getCourseById(id);

        course.setName(dto.getName());
        course.setDepartmentId(dto.getDepartmentId());

        List<Semester> semesters = dto.getSemesters()
                .stream()
                .map(this::mapSemester)
                .collect(Collectors.toList());

        course.setSemesters(semesters);

        return courseRepository.save(course);
    }

    // DELETE
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    // ---------- mapping helpers ----------

    private Semester mapSemester(SemesterDTO dto) {
        Semester semester = new Semester();
        semester.setSemesterNumber(dto.getSemesterNumber());

        List<Subject> subjects = dto.getSubjects()
                .stream()
                .map(this::mapSubject)
                .collect(Collectors.toList());

        semester.setSubjects(subjects);
        return semester;
    }

    private Subject mapSubject(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setCode(dto.getCode());
        subject.setName(dto.getName());
        subject.setCredits(dto.getCredits());
        return subject;
    }
}