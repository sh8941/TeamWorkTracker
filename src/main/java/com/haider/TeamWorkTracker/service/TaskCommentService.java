package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.config.SecurityUtils;
import com.haider.TeamWorkTracker.dtos.request.TaskCommentRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskCommentResponse;
import com.haider.TeamWorkTracker.entity.TaskCommentEntity;
import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.entity.UserEntity;
import com.haider.TeamWorkTracker.enums.Visibility;
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
        UserEntity currentUser = securityUtils.getCurrentUser();
        TaskEntity taskEntity = taskService.getTaskEntity(taskCommentRequest.getTaskId());

        if (! taskEntity.getCreatedBy().getId().equals(currentUser.getId()) &&
        ! taskEntity.getVisibility().equals(Visibility.PUBLIC) &&
        ! taskEntity.getUsers().contains(currentUser)) {
            throw new UnauthorizedException("Unauthorized");
        }

        taskCommentEntity.setTask(taskEntity);
        taskCommentEntity.setUser(currentUser);
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
        UserEntity currentUser = securityUtils.getCurrentUser();

        if (! taskCommentEntity.getTask().getCreatedBy().getId().equals(currentUser.getId()) &&
                ! taskCommentEntity.getTask().getVisibility().equals(Visibility.PUBLIC) &&
                ! taskCommentEntity.getTask().getUsers().contains(currentUser)) {
            throw new UnauthorizedException("Unauthorized");
        }

        TaskCommentResponse taskCommentResponse = new TaskCommentResponse();

        taskCommentResponse.setComment(taskCommentEntity.getComment());
        taskCommentResponse.setTaskId(taskCommentEntity.getTask().getId());
        taskCommentResponse.setUserId(currentUser.getId());
        taskCommentResponse.setId(taskCommentEntity.getId());

        return taskCommentEntity;
    }

    public TaskCommentResponse getById(Long id) {
        TaskCommentEntity taskCommentEntity = getTaskCommentEntityById(id);
        UserEntity currentUser = securityUtils.getCurrentUser();
        if (! taskCommentEntity.getUser().getId().equals(currentUser.getId()) &&
        ! currentUser.getRole().getRoleName().equals("ADMIN")) {
            throw new UnauthorizedException("Unauthorized");
        }

        TaskCommentResponse taskCommentResponse = new TaskCommentResponse();
        taskCommentResponse.setComment(taskCommentEntity.getComment());
        taskCommentResponse.setTaskId(taskCommentEntity.getTask().getId());
        taskCommentResponse.setUserId(taskCommentEntity.getUser().getId());
        taskCommentResponse.setId(taskCommentEntity.getId());

        return taskCommentResponse;
    }

    public void deleteBy(Long id) {
        TaskCommentEntity taskCommentEntity = getTaskCommentEntityById(id);
        UserEntity currentUser = securityUtils.getCurrentUser();

        if (! taskCommentEntity.getUser().getId().equals(currentUser.getId()) &&
                ! currentUser.getRole().getRoleName().equals("ADMIN")) {
            throw new UnauthorizedException("Unauthorized");
        }

        taskCommentEntity.setActive(false);
        taskCommentRepo.save(taskCommentEntity);
    }
}
