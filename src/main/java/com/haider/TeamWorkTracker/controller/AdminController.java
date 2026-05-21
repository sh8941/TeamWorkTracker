package com.haider.TeamWorkTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "Perform operations as admin")
public class AdminController {
    @Operation(summary = "To check login and return status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status")
    })
    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello Admin...");
    }
}
