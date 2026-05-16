package com.haider.TeamWorkTracker.repo;

import com.haider.TeamWorkTracker.entity.TaskCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskCommentRepo extends JpaRepository<TaskCommentEntity, Long> {
   Optional<TaskCommentEntity> findByIdAndActiveTrue(Long id);
}
