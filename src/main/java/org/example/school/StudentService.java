package org.example.school;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    public StudentService(StudentRepository studentRepository,
                          StudentValidator studentValidator) {
        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
    }

    public static List<Student> createStudents(List<Student> students) {

        for (Student student : students) {
            studentValidator.validate(student);
        }

        return studentRepository.saveAll(students);
    }
}
