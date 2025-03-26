package com.example.Login_spring_security.service;

import com.example.Login_spring_security.model.Users;
import com.example.Login_spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public Users register(Users user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
       return userRepo.save(user);

    }


}
