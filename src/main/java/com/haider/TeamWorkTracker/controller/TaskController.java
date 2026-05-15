package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.TaskRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskResponse;
import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.addTask(taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
