package com.example.Login_spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students=new ArrayList<>(List.of(
            new Student(1,"Vaibhav",97),
            new Student(2,"om",87)
    ));

    @GetMapping("/student")
    public List<Student> getStudents()
    {
        return students;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student)
    {
        students.add(student);
        return student;
    }

    @GetMapping("/csrf_token")
    public CsrfToken getCSRFToken(HttpServletRequest req)
    {
        return (CsrfToken) req.getAttribute("_csrf");
    }
}
