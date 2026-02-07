package com.example.StudentPortal.service;

import com.example.StudentPortal.dto.request.DepartmentRequestDTO;
import com.example.StudentPortal.model.Department;
import com.example.StudentPortal.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    //  CREATE
    public Department create(DepartmentRequestDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        return departmentRepository.save(department);
    }

    //  GET ALL
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    // GET BY ID
    public Department getById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    //  UPDATE
    public Department update(String id, DepartmentRequestDTO dto) {
        Department department = getById(id);
        department.setName(dto.getName());
        return departmentRepository.save(department);
    }

    //  DELETE
    public void delete(String id) {
        Department department = getById(id);
        departmentRepository.delete(department);
    }
}