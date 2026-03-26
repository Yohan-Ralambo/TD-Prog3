package org.example.school;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolCOntroller {

    @GetMapping("/welcome")
    public String Welcome(@RequestParam String name) {
        return "Welcome " + name;
    }

    private List<Student> students = new ArrayList<>();

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {

        students.addAll(newStudents);

        return students.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader("Accept") String accept) {

        if (!accept.contains("application/json") && !accept.contains("text/plain")) {
            return ResponseEntity
                    .status(406)
                    .body("Format non supporté");
        }

        if (accept.contains("text/plain")) {
            String result = students.stream()
                    .map(s -> s.getFirstName() + " " + s.getLastName())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.ok().body(students);
    }

}
