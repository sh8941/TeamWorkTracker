package com.haider.TeamWorkTracker.dtos.response;

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
public class TaskCommentResponse {
    Long id;
    String comment;
    Long taskId;
    Long userId;
}
