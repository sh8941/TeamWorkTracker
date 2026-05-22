package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.UserRequest;
import com.haider.TeamWorkTracker.dtos.response.UserResponse;
import com.haider.TeamWorkTracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "To register and delete a user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "To register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.addUser(
                userRequest.getUsername(),
                userRequest.getPassword()
        );
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Fetch current user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details fetched"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(){
        UserResponse userResponse = userService.getMyDetails();
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Delete current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User Deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(){
        userService.deleteMyAccount();
        return ResponseEntity.noContent().build();
    }
}
