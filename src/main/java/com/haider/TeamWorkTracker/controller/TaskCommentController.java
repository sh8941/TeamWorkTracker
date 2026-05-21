package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.TaskCommentRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskCommentResponse;
import com.haider.TeamWorkTracker.service.TaskCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Tag(name = "Comment Controller", description = "To add and delete comment on task")
public class TaskCommentController {
    @Autowired
    TaskCommentService taskCommentService;

    @Operation(summary = "Add comment on specific task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment created"),
            @ApiResponse(responseCode = "404", description = "User or Task not found")
    })
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody TaskCommentRequest taskCommentRequest) {
        TaskCommentResponse taskCommentResponse = taskCommentService.addComment(taskCommentRequest);
        return ResponseEntity.ok().body(taskCommentResponse);
    }

    @Operation(summary = "Get comment by specific task_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment fetched"),
            @ApiResponse(responseCode = "404", description = "User or Task or Comment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskComment(@PathVariable Long id) {
        TaskCommentResponse taskResponse = taskCommentService.getById(id);
        return ResponseEntity.ok().body(taskResponse);
    }

    @Operation(summary = "Delete comment by specific task_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted"),
            @ApiResponse(responseCode = "404", description = "User or Task or Comment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskComment(@PathVariable Long id) {
        taskCommentService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }
}
