package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.entity.RoleEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
import com.haider.TeamWorkTracker.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public RoleEntity findByRoleId(Long id) {
    return roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role with id " + id + " not found"));
    }
}
