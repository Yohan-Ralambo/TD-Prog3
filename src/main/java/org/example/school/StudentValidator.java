package org.example.school;

import org.springframework.stereotype.Component;

@Component
public class StudentValidator {

    public void validate(Student student) {
        if (student.getReference() == null || student.getReference().isBlank()) {
            throw new IllegalArgumentException("Student reference cannot be null or blank");
        }
    }
}