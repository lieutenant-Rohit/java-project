package com.example.StudentPortal.repository;

import com.example.StudentPortal.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}