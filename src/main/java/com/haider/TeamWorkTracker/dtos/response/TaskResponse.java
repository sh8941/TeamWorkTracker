package com.haider.TeamWorkTracker.dtos.response;

import ch.qos.logback.classic.ViewStatusMessagesServlet;
import com.haider.TeamWorkTracker.enums.Priority;
import com.haider.TeamWorkTracker.enums.Status;
import com.haider.TeamWorkTracker.enums.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Schema(description = "TaskResponse")
public class TaskResponse {
    @Schema(description = "Unique ID of the task", example = "101")
    private Long id;
    @Schema(description = "Title of the task", example = "Implement JWT")
    private String title;
    @Schema(description = "Description of the task", example = "implement jwt for request authentication")
    private String description;
    @Schema(description = "User_id of the creator", example = "5")
    private Long createdBy;
    @Schema(description = "Visibility of the task", example = "TEAM")
    private Visibility visibility;
    @Schema(description = "Status of the task", example = "COMPLETED")
    private Status status;
    @Schema(description = "Priority of the task", example = "HIGH")
    private Priority priority;
    @Schema(
            description = "Set of assignee user IDs",
            example = "[1, 2, 3]"
    )
    private Set<Long> assignees = new HashSet<>();
}
