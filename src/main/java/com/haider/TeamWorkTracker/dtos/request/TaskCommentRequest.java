package com.haider.TeamWorkTracker.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCommentRequest {
    @Schema(
            description = "What comment ?",
            example = "Task looking interesting",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Comment can not be empty")
    @Size(min = 2, max = 500, message = "Comment must be between 2 and 500 characters")
    String comment;

    @Schema(
            description = "Id for the task, on which to comment",
            example = "Task_id",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Task ID is required")
    @Positive(message = "Task ID must be a positive number")
    Long taskId;
}
