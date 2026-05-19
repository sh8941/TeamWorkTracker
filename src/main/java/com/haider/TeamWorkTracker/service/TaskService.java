package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.config.SecurityUtils;
import com.haider.TeamWorkTracker.dtos.request.TaskRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskResponse;
import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.entity.UserEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
import com.haider.TeamWorkTracker.exception.UnauthorizedException;
import com.haider.TeamWorkTracker.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityUtils securityUtils;

    public TaskEntity getTaskEntity(Long id) {
        return taskRepo.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found..."));
    }

    public TaskResponse addTask(TaskRequest taskRequest) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskRequest.getTitle());
        taskEntity.setDescription(taskRequest.getDescription());
        taskEntity.setCreatedBy(securityUtils.getCurrentUser());
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
        taskResponse.setCreatedBy(saved.getCreatedBy().getId());
        taskResponse.setAssignees(taskRequest.getAssignees());
        taskResponse.setVisibility(saved.getVisibility());
        taskResponse.setPriority(saved.getPriority());
        taskResponse.setStatus(saved.getStatus());
        return taskResponse;
    }

    public TaskResponse getById(Long id) {

        TaskResponse taskResponse = new TaskResponse();
        TaskEntity taskEntity = getTaskEntity(id);
        UserEntity currentUser = securityUtils.getCurrentUser();

        if (! taskEntity.getCreatedBy().getId().equals(currentUser.getId()) &&
        ! currentUser.getRole().getRoleName().equals("ADMIN")) {
            throw new UnauthorizedException("You are not allowed to access this task");
        }

        taskResponse.setId(taskEntity.getId());
        taskResponse.setTitle(taskEntity.getTitle());
        taskResponse.setDescription(taskEntity.getDescription());
        taskResponse.setCreatedBy(taskEntity.getCreatedBy().getId());
        taskResponse.setVisibility(taskEntity.getVisibility());
        taskResponse.setPriority(taskEntity.getPriority());
        taskResponse.setStatus(taskEntity.getStatus());

        HashSet<Long> assignees = new HashSet<>();
        taskEntity.getUsers().forEach(a -> assignees.add(a.getId()));
        taskResponse.setAssignees(assignees);

        return  taskResponse;
    }

    public void deleteById(Long id) {
        UserEntity currentUser = securityUtils.getCurrentUser();
        TaskEntity taskEntity = getTaskEntity(id);

        if (! taskEntity.getCreatedBy().getId().equals(currentUser.getId()) &&
        ! currentUser.getRole().getRoleName().equals("ADMIN")) {
            throw new UnauthorizedException("You are not allowed to delete this task");
        }

        taskEntity.setActive(false);
        taskRepo.save(taskEntity);
    }

    public List<TaskResponse> getMyTasks() {
        UserEntity userEntity = securityUtils.getCurrentUser();
        List<TaskEntity> taskEntities = taskRepo.findAllByCreatedById(userEntity.getId());
        List<TaskResponse> taskResponses = new ArrayList<>();
        taskEntities.forEach(taskEntity -> {
            taskResponses.add(toResponse(taskEntity));
        });
        return taskResponses;
    }

    public List<TaskResponse> getAssignedTasks() {
        UserEntity userEntity = securityUtils.getCurrentUser();
        List<TaskEntity> taskEntities = taskRepo.findAllByUsers_Id(userEntity.getId());
        List<TaskResponse> taskResponses = new ArrayList<>();
        taskEntities.forEach(taskEntity -> {
            taskResponses.add(toResponse(taskEntity));
        });
        return taskResponses;
    }

    public TaskResponse toResponse(TaskEntity taskEntity) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(taskEntity.getId());
        taskResponse.setTitle(taskEntity.getTitle());
        taskResponse.setDescription(taskEntity.getDescription());
        taskResponse.setCreatedBy(taskEntity.getCreatedBy().getId());
        taskResponse.setVisibility(taskEntity.getVisibility());
        taskResponse.setPriority(taskEntity.getPriority());
        taskResponse.setStatus(taskEntity.getStatus());
        Set<Long> assignees = new HashSet<>();
        taskEntity.getUsers().forEach(userEntity ->
            {assignees.add(userEntity.getId());});
        taskResponse.setAssignees(assignees);
        return taskResponse;
    }

}
