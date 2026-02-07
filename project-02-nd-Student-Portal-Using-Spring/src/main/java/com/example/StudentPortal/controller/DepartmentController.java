package com.example.StudentPortal.controller;

import com.example.StudentPortal.dto.request.DepartmentRequestDTO;
import com.example.StudentPortal.model.Department;
import com.example.StudentPortal.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // 1️⃣ CREATE
    @PostMapping
    public ResponseEntity<Department> create(@RequestBody DepartmentRequestDTO dto) {
        return ResponseEntity.ok(departmentService.create(dto));
    }

    // 2️⃣ GET ALL
    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    // 3️⃣ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }

    // 4️⃣ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Department> update(
            @PathVariable String id,
            @RequestBody DepartmentRequestDTO dto
    ) {
        return ResponseEntity.ok(departmentService.update(id, dto));
    }

    // 5️⃣ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}