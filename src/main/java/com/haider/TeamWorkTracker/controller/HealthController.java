package com.haider.TeamWorkTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Controller", description = "Check health status of the application")
public class HealthController {
    @Operation(summary = "Return the status of application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application is running...")
    })
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("Health is ok..");
    }
}
