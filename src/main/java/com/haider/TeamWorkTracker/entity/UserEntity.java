package com.haider.TeamWorkTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(hidden = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity role;
    @ManyToMany(mappedBy = "users")
    private Set<TaskEntity> tasks = new HashSet<>();
}
