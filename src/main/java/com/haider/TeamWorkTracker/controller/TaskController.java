package com.haider.TeamWorkTracker.controller;

import com.haider.TeamWorkTracker.dtos.request.TaskRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskResponse;
import com.haider.TeamWorkTracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "Task Controller", description = "To create and delete a task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Operation(summary = "Add comment on specific task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.addTask(taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Fetch the list of created task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task list fetched"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/created")
    public ResponseEntity<?> getCreatedTask() {
        List<TaskResponse> taskResponses = taskService.getMyTasks();
        return ResponseEntity.ok(taskResponses);
    }

    @Operation(summary = "Fetch a specific task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task is fetched"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
  @GetMapping("/{id}")
  public ResponseEntity<?> getCreatedTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(summary = "Fetch the list of assigned task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task list is fetched"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/assigned")
    public ResponseEntity<?> getAssignedTask() {
        List<TaskResponse> taskResponses = taskService.getAssignedTasks();
        return ResponseEntity.ok(taskResponses);
    }

    @Operation(summary = "Delete a specific task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task is Deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
