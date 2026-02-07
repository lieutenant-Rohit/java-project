package com.example.StudentPortal.service;

import com.example.StudentPortal.dto.response.StudentFullResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class StudentAggregationService {

    private final MongoTemplate mongoTemplate;

    public StudentFullResponseDTO getStudentWithCourseAndDepartment(String studentId) {

        Aggregation aggregation = newAggregation(

                // 1️⃣ Match student
                match(Criteria.where("_id").is(studentId)),

                // 2️⃣ Setup Joins
                project()
                        .andInclude("name", "email", "age", "courseId", "currentSemester")
                        .and(ConvertOperators.ToObjectId.toObjectId("$courseId")).as("objCourseId"),

                // 3️⃣ Join Course
                lookup("course", "objCourseId", "_id", "course"),
                unwind("course", true),

                // 4️⃣ Setup Department Join
                project()
                        .andInclude("name", "email", "age", "course", "currentSemester")
                        .and(ConvertOperators.ToObjectId.toObjectId("$course.departmentId")).as("objDeptId"),

                // 5️⃣ Join Department
                lookup("department", "objDeptId", "_id", "department"),
                unwind("department", true),

                // 6️⃣ FINAL PROJECTION
                project()
                        .and("_id").as("id")
                        .and("name").as("name")
                        .and("email").as("email")
                        .and("age").as("age")

                        // ✅ ADD THIS LINE: Pass the currentSemester to the output
                        .and("currentSemester").as("currentSemester")

                        // Course ID & Name
                        .and(ConvertOperators.ToString.toString("$course._id")).as("course.id")
                        .and("course.name").as("course.name")

                        // FILTER: Only return the matching semester
                        .and(ArrayOperators.Filter.filter("course.semesters")
                                .as("sem")
                                .by(ComparisonOperators.Eq.valueOf("sem.semesterNumber").equalTo("currentSemester"))
                        ).as("course.semesters")

                        // Department Details
                        .and(ConvertOperators.ToString.toString("$department._id")).as("course.department.id")
                        .and("department.name").as("course.department.name")
        );

        return mongoTemplate
                .aggregate(aggregation, "student", StudentFullResponseDTO.class)
                .getUniqueMappedResult();
    }
}