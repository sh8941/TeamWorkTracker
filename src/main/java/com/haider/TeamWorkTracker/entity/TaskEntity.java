package com.haider.TeamWorkTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haider.TeamWorkTracker.enums.Priority;
import com.haider.TeamWorkTracker.enums.Status;
import com.haider.TeamWorkTracker.enums.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(hidden = true)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity createdBy;
    @ManyToMany
    @JoinTable(
            name = "task_users",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users = new HashSet<>();
    private LocalDateTime createdAt;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.TEAM;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<TaskCommentEntity> taskComments = new ArrayList<>();
}
