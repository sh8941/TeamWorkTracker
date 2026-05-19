package com.haider.TeamWorkTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String username;
    private String password;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity role;
    @ManyToMany(mappedBy = "users")
    private Set<TaskEntity> tasks = new HashSet<>();
}
