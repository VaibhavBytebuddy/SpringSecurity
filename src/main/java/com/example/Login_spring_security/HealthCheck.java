package com.example.Login_spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public String healthCheck(HttpServletRequest req)
    {
        return "Health is okk"+req.getSession().getId();
    }
}
