package com.haider.TeamWorkTracker.dtos.request;

import lombok.Data;

import java.util.Set;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private Set<Long> assignees;
}
