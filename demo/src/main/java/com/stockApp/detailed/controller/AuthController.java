package com.stockApp.detailed.controller;


import com.stockApp.detailed.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestHeader("UserId") String userId) {
        return jwtUtil.generateToken(userId);
    }
}