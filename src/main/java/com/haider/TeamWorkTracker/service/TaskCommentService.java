package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.config.SecurityUtils;
import com.haider.TeamWorkTracker.dtos.request.TaskCommentRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskCommentResponse;
import com.haider.TeamWorkTracker.entity.TaskCommentEntity;
import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.entity.UserEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
import com.haider.TeamWorkTracker.exception.UnauthorizedException;
import com.haider.TeamWorkTracker.repo.TaskCommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCommentService {
    @Autowired
    private TaskCommentRepo taskCommentRepo;
    @Autowired
    private TaskService taskService;
    @Autowired
    SecurityUtils securityUtils;

    public TaskCommentResponse addComment(TaskCommentRequest taskCommentRequest) {
        TaskCommentEntity taskCommentEntity = new TaskCommentEntity();
        taskCommentEntity.setComment(taskCommentRequest.getComment());
        TaskEntity taskEntity = taskService.getTaskEntity(taskCommentRequest.getTaskId());
        taskCommentEntity.setTask(taskEntity);
        taskCommentEntity.setUser(securityUtils.getCurrentUser());
        taskCommentEntity.setActive(true);

        TaskCommentEntity saved = taskCommentRepo.save(taskCommentEntity);

        TaskCommentResponse taskCommentResponse = new TaskCommentResponse();
        taskCommentResponse.setComment(saved.getComment());
        taskCommentResponse.setTaskId(saved.getTask().getId());
        taskCommentResponse.setUserId(securityUtils.getCurrentUser().getId());
        taskCommentResponse.setId(taskCommentEntity.getId());

        return taskCommentResponse;
    }

    public TaskCommentEntity getTaskCommentEntityById(Long id) {
        TaskCommentEntity taskCommentEntity = taskCommentRepo.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ResourceNotFoundException("TaskComment not found with id: " + id));
        UserEntity userEntity = securityUtils.getCurrentUser();

        if (! taskCommentEntity.getUser().equals(userEntity)) {
            throw new UnauthorizedException("Unauthorized");
        }

        TaskCommentResponse taskCommentResponse = new TaskCommentResponse();

        taskCommentResponse.setComment(taskCommentEntity.getComment());
        taskCommentResponse.setTaskId(taskCommentEntity.getTask().getId());
        taskCommentResponse.setUserId(securityUtils.getCurrentUser().getId());
        taskCommentResponse.setId(taskCommentEntity.getId());

        return taskCommentEntity;
    }

    public TaskCommentResponse getById(Long id) {
        TaskCommentEntity taskCommentEntity = getTaskCommentEntityById(id);
        TaskCommentResponse taskCommentResponse = new TaskCommentResponse();
        taskCommentResponse.setComment(taskCommentEntity.getComment());
        taskCommentResponse.setTaskId(taskCommentEntity.getTask().getId());
        taskCommentResponse.setUserId(taskCommentEntity.getUser().getId());
        taskCommentResponse.setId(taskCommentEntity.getId());

        return taskCommentResponse;
    }

    public void deleteBy(Long id) {
        TaskCommentEntity taskCommentEntity = getTaskCommentEntityById(id);
        UserEntity userEntity = securityUtils.getCurrentUser();
        if (! taskCommentEntity.getUser().equals(userEntity)) {
            throw new UnauthorizedException("Unauthorized");
        }
        taskCommentEntity.setActive(false);
        taskCommentRepo.save(taskCommentEntity);
    }
}
