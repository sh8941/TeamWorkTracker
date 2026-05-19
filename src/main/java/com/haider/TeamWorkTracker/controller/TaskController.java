package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.TaskRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskResponse;
import com.haider.TeamWorkTracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/created")
    public ResponseEntity<?> getCreatedTask() {
        List<TaskResponse> taskResponses = taskService.getMyTasks();
        return ResponseEntity.ok(taskResponses);
    }

  @GetMapping("/{id}")
  public ResponseEntity<?> getCreatedTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/assigned")
    public ResponseEntity<?> getAssignedTask() {
        List<TaskResponse> taskResponses = taskService.getAssignedTasks();
        return ResponseEntity.ok(taskResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
