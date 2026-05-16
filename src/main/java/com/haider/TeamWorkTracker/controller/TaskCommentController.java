package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.TaskCommentRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskCommentResponse;
import com.haider.TeamWorkTracker.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class TaskCommentController {
    @Autowired
    TaskCommentService taskCommentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody TaskCommentRequest taskCommentRequest) {
        TaskCommentResponse taskCommentResponse = taskCommentService.addComment(taskCommentRequest);
        return ResponseEntity.ok().body(taskCommentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskComment(@PathVariable Long id) {
        TaskCommentResponse taskResponse = taskCommentService.getById(id);
        return ResponseEntity.ok().body(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskComment(@PathVariable Long id) {
        taskCommentService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }
}
