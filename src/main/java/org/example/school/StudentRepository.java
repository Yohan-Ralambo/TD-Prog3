package org.example.school;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public List<Student> saveAll(List<Student> students) {
        students.addAll(students);
        return students;
    }
}