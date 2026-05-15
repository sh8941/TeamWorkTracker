package com.haider.TeamWorkTracker.service;

import com.haider.TeamWorkTracker.dtos.response.UserResponse;
import com.haider.TeamWorkTracker.entity.UserEntity;
import com.haider.TeamWorkTracker.exception.ResourceNotFoundException;
import com.haider.TeamWorkTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserEntity getUserEntity(Long id){
        return userRepo.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found..."));
    }

    public UserResponse addUser(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setActive(true);
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

    public void deleteById(Long id) {
        UserEntity userEntity = getUserEntity(id);
        userEntity.setActive(false);
        userRepo.save(userEntity);
    }
}
