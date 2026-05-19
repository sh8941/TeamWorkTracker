package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.config.SecurityUtils;
import com.haider.TeamWorkTracker.dtos.response.UserResponse;
import com.haider.TeamWorkTracker.entity.UserEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
import com.haider.TeamWorkTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;

    public UserEntity getUserEntity(Long id){
        return userRepo.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ResourceNotFoundException("trying to assign invalid user :"+id));
    }

    public UserEntity getUserEntity(String username){
        return userRepo.findByUsernameAndActiveTrue(username).orElseThrow(() ->
                new ResourceNotFoundException("User not found..."));
    }

    public UserResponse addUser(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setActive(true);
        userEntity.setRole(roleService.findByRoleId(2L));
        UserEntity saved = userRepo.save(userEntity);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(saved.getId());
        userResponse.setUsername(saved.getUsername());
        return userResponse;
    }

    public UserResponse getById(Long id) {
        UserEntity userEntity = getUserEntity(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUsername(userEntity.getUsername());
        return userResponse;
    }

    public void deleteMyAccount() {
        UserEntity userEntity = securityUtils.getCurrentUser();
        userEntity.setActive(false);
        userRepo.save(userEntity);
    }

    public UserResponse getMyDetails() {
        UserEntity userEntity = securityUtils.getCurrentUser();
        return new UserResponse(userEntity.getId(),userEntity.getUsername());
    }
}
