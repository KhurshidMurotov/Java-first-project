package com.company.firstprojectspring.service;


import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.UserDto;
import com.company.firstprojectspring.module.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ApiResponse<UserDto> createUser(UserDto dto);
    ApiResponse<UserDto> getUserById(Integer id);
    ApiResponse<UserDto> updateUserById(Integer id, UserDto dto);
    ApiResponse<UserDto> deleteUserById(Integer userId);
    ApiResponse<List<UserDto>> getAllUsers();

}
