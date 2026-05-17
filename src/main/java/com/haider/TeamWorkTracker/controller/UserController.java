package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.UserRequest;
import com.haider.TeamWorkTracker.dtos.response.UserResponse;
import com.haider.TeamWorkTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.addUser(
                userRequest.getUsername(),
                userRequest.getPassword()
        );
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(){
        UserResponse userResponse = userService.getMyDetails();
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(){
        userService.deleteMyAccount();
        return ResponseEntity.noContent().build();
    }
}
