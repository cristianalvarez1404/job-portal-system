package com.zosh.job.service.imp;

import com.zosh.job.dto.response.UserResponse;
import com.zosh.job.model.User;
import com.zosh.job.payload.UpdateUserRequest;
import com.zosh.job.service.UserService;

import java.util.List;

public class UserServiceImp implements UserService {
    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse updateProfile(String email, UpdateUserRequest req) {
        return null;
    }

    @Override
    public UserResponse suspendUser(Long id) {
        return null;
    }

    @Override
    public UserResponse activateUser(Long id) {
        return null;
    }

    @Override
    public UserResponse deleteUser(Long id) {
        return null;
    }
}
