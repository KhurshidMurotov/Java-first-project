package com.company.firstprojectspring.controller;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.UserDto;
import com.company.firstprojectspring.module.Users;
import com.company.firstprojectspring.service.UserService;
import com.company.firstprojectspring.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
// todo: localhost -> 127.0.0.1
// todo: localhost: 8080/users

@RestController       // todo:  bunda barcha chiquvchi qiymatlar JSONga ozgaradi, Chunki unda ResponseBody methodi bor
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }


    // todo: @RequestBody JSON malumotni classga o'zgartirib beradi, @ResponseBody classdagi malumotlarni ->
    // todo: -> JSONga o'zgartirib beradi


    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse<UserDto> createUser(@RequestBody @Valid UserDto dto) {
        return this.userService.createUser(dto);
    }

    //todo: localhost: 8080/users?key=value&key=value&key=value
    //todo: localhost: 8080/users?key=value

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse<UserDto> getUserByID(@RequestParam(value = "id") Integer userId) {
        return this.userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse<UserDto> updateUserById(
            @RequestParam(value = "id") Integer userId,
            @RequestBody UserDto dto
    ) {

        return this.userService.updateUserById(userId, dto);

    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse<UserDto> deleteUserById(@RequestParam(value = "id") Integer userId) {
        return this.userService.deleteUserById(userId);
    }


    //todo: localhost:8080/users/get-all

    @RequestMapping(method = RequestMethod.GET, value = "/get-all")
    public ApiResponse<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }


}
