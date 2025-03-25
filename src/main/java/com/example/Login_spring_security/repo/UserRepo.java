package com.example.Login_spring_security.repo;

import com.example.Login_spring_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserRepo extends JpaRepository<Users,Integer > {
    Users findByUsername(String username);
}
