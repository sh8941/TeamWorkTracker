package com.haider.TeamWorkTracker.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @Schema(
            description = "Unique ID of the user",
            example = "1"
    )
    private Long id;
    @Schema(
            description = "Username of the user",
            example = "john_doe"
    )
    private String username;
}
