package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.dtos.request.TaskRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskResponse;
import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.entity.UserEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
import com.haider.TeamWorkTracker.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserService userService;

    public TaskEntity getTaskEntity(Long id) {
        return taskRepo.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found..."));
    }

    public TaskResponse addTask(TaskRequest taskRequest) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskRequest.getTitle());
        taskEntity.setDescription(taskRequest.getDescription());
        taskEntity.setCreatedBy(1L);
        taskEntity.setActive(true);

        Set<UserEntity> users = taskRequest.getAssignees()
                .stream()
                .map(userService::getUserEntity)
                .collect(Collectors.toSet());

        taskEntity.setUsers(users);
        taskEntity.setCreatedAt(LocalDateTime.now());

        TaskEntity saved =  taskRepo.save(taskEntity);
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(saved.getId());
        taskResponse.setTitle(saved.getTitle());
        taskResponse.setDescription(saved.getDescription());
        taskResponse.setCreatedBy(saved.getCreatedBy());
        taskResponse.setAssignees(taskRequest.getAssignees());
        taskResponse.setVisibility(saved.getVisibility());
        taskResponse.setPriority(saved.getPriority());
        taskResponse.setStatus(saved.getStatus());
        return taskResponse;
    }

    public TaskResponse getById(Long id) {
        TaskResponse taskResponse = new TaskResponse();
        TaskEntity taskEntity = getTaskEntity(id);
        taskResponse.setId(taskEntity.getId());
        taskResponse.setTitle(taskEntity.getTitle());
        taskResponse.setDescription(taskEntity.getDescription());
        taskResponse.setCreatedBy(taskEntity.getCreatedBy());
        taskResponse.setVisibility(taskEntity.getVisibility());
        taskResponse.setPriority(taskEntity.getPriority());
        taskResponse.setStatus(taskEntity.getStatus());

        HashSet<Long> assignees = new HashSet<>();
        taskEntity.getUsers().forEach(a -> assignees.add(a.getId()));
        taskResponse.setAssignees(assignees);

        return  taskResponse;
    }

    public void deleteById(Long id) {
        TaskEntity taskEntity = getTaskEntity(id);
        taskEntity.setActive(false);
        taskRepo.save(taskEntity);
        return;
    }
}
