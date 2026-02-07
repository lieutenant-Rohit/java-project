package com.example.StudentPortal.repository;

import com.example.StudentPortal.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {
}