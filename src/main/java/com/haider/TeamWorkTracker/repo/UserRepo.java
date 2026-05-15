package com.haider.TeamWorkTracker.repo;

import com.haider.TeamWorkTracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdAndActiveTrue(Long id);
}
