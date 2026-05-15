package com.haider.TeamWorkTracker.dtos.response;

import ch.qos.logback.classic.ViewStatusMessagesServlet;
import com.haider.TeamWorkTracker.enums.Priority;
import com.haider.TeamWorkTracker.enums.Status;
import com.haider.TeamWorkTracker.enums.Visibility;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Long createdBy;
    private Visibility visibility;
    private Status status;
    private Priority priority;
    private Set<Long> assignees = new HashSet<>();
}
