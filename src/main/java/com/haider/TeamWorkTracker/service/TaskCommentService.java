package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.dtos.request.TaskCommentRequest;
import com.haider.TeamWorkTracker.dtos.response.TaskCommentResponse;
import com.haider.TeamWorkTracker.dtos.response.TaskResponse;
import com.haider.TeamWorkTracker.entity.TaskCommentEntity;
import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
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
    UserService userService;

    public TaskCommentResponse addComment(TaskCommentRequest taskCommentRequest) {
        TaskCommentEntity taskCommentEntity = new TaskCommentEntity();
        taskCommentEntity.setComment(taskCommentRequest.getComment());
        TaskEntity taskEntity = taskService.getTaskEntity(taskCommentRequest.getTaskId());
        taskCommentEntity.setTask(taskEntity);
        taskCommentEntity.setUser(userService.getUserEntity(taskCommentRequest.getUserId()));
        taskCommentEntity.setActive(true);

        TaskCommentEntity saved = taskCommentRepo.save(taskCommentEntity);

        TaskCommentResponse taskCommentResponse = new TaskCommentResponse();
        taskCommentResponse.setComment(saved.getComment());
        taskCommentResponse.setTaskId(saved.getTask().getId());
        taskCommentResponse.setUserId(saved.getUser().getId());
        taskCommentResponse.setId(taskCommentEntity.getId());

        return taskCommentResponse;
    }

    public TaskCommentEntity getTaskCommentEntityById(Long id) {
        return taskCommentRepo.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ResourceNotFoundException("TaskComment not found with id: " + id));
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
        taskCommentEntity.setActive(false);
        taskCommentRepo.save(taskCommentEntity);
    }
}
