package com.haider.TeamWorkTracker.dtos.request;

import com.haider.TeamWorkTracker.entity.TaskEntity;
import com.haider.TeamWorkTracker.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCommentRequest {
    String comment;
    Long taskId;
    Long userId;
}
