package com.haider.TeamWorkTracker.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
@Schema(description = "Request for creating a task")
public class TaskRequest {
    @Schema(
            description = "Title of the task",
            example = "Complete backend project",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(
            description = "Detailed description of the task",
            example = "Complete backend project",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(max = 500, message = "Description can't exceed 500 characters")
    private String description;

    @Schema(
            description = "List of user IDs assigned to the task",
            example = "[1, 2, 3]"
    )
    @NotNull(message = "Assignees list cannot be null")
    @NotEmpty(message = "At least one assignee is required")
    private Set<@Positive(message = "Assignee ID must be positive") Long> assignees;
}
