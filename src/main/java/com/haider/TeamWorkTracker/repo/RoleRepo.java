package com.haider.TeamWorkTracker.repo;

import com.haider.TeamWorkTracker.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {}
