package com.realestate.management.controller;

import com.realestate.management.Dto.request.LoginRequest;
import com.realestate.management.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @GetMapping
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        return new ApiResponse(true, "Login successful",null , LocalDateTime.now());
    }
}
