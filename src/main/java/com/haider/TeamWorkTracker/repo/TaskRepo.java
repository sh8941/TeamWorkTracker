package com.haider.TeamWorkTracker.repo;

import com.haider.TeamWorkTracker.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByIdAndActiveTrue(Long id);

    List<TaskEntity> findAllByCreatedById(Long id);

    List<TaskEntity> findAllByUsers_Id(Long id);
}
