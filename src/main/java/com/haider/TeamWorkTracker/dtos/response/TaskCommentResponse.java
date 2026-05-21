package com.haider.TeamWorkTracker.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Task comment response")
public class TaskCommentResponse {
    @Schema(description = "Unique ID for the comment", example = "110")
    Long id;
    @Schema(description = "What is the comment", example = "Task is amaing")
    String comment;
    @Schema(description = "Unique ID of the specif task", example = "12")
    Long taskId;
    @Schema(description = "Unique ID of the user who make this comment", example = "72")
    Long userId;
}
