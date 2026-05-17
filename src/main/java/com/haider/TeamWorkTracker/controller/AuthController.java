package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.AuthRequest;
import com.haider.TeamWorkTracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AuthRequest authRequest){
        String token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

}
