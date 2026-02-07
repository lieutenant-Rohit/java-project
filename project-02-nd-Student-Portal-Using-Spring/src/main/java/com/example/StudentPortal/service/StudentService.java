package com.example.StudentPortal.service;

import com.example.StudentPortal.dto.request.StudentRequestDTO;
import com.example.StudentPortal.dto.response.StudentResponseDTO;
import com.example.StudentPortal.model.Student;
import com.example.StudentPortal.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final MongoTemplate mongoTemplate;

    //  CREATE
    public Student create(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        // Save current semester
        student.setCurrentSemester(dto.getCurrentSemester());

        student.setCourseId(dto.getCourseId());
        return studentRepository.save(student);
    }

    // GET ALL
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    //  GET BY ID
    public Student getById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    //  UPDATE
    public Student update(String id, StudentRequestDTO dto) {
        Student student = getById(id);

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        // Update current semester
        student.setCurrentSemester(dto.getCurrentSemester());

        student.setCourseId(dto.getCourseId());

        return studentRepository.save(student);
    }

    //  DELETE
    public void delete(String id) {
        Student student = getById(id);
        studentRepository.delete(student);
    }

    // GET STUDENT WITH COURSE (Simple Aggregation)
    public StudentResponseDTO getStudentWithCourse(String studentId) {

        Aggregation aggregation = newAggregation(
                match(Criteria.where("_id").is(studentId)),
                lookup("course", "courseId", "_id", "course"),
                unwind("course", true) // Added true for null safety
        );

        return mongoTemplate.aggregate(
                aggregation,
                "student",
                StudentResponseDTO.class
        ).getUniqueMappedResult();
    }
}