package org.example.school;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SchoolCOntroller {

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam String name) {
       if (name == null) {
           return ResponseEntity
           .status(400)
                   .body("Name is required");

       }
        return ResponseEntity
        .status(HttpStatus.OK)
        .body("Welcome " + name);
    }

    private List<Student> students = new ArrayList<>();

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            students.add(student);
            return ResponseEntity
            .status(HttpStatus.CREATED)
                .body(student);
        } catch (Exception e){
            return ResponseEntity
                    .status(500)
                    .body("Erreur interne du serveur");
    }
}

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader("Accept") String accept) {

        if (!accept.contains("application/json") && !accept.contains("text/plain")) {
            return ResponseEntity
                    .status(501)
                    .body("Erreur interne du serveur");
        }

        try {
            accept.equals("text/plain");
            accept.equals("application/json");
            return ResponseEntity
                    .status(200)
                    .body(students);
        } catch (Exception e) {
            return ResponseEntity
            .status(500)
            .body("Erreur interne du serveur");
        }
    }

}
