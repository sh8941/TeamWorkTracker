package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.AuthRequest;
import com.haider.TeamWorkTracker.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Controller", description = "To authenticate a user request")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Create and Return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AuthRequest authRequest){
        String token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

}
