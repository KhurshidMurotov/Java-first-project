package com.company.firstprojectspring.service.impl;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.dto.UserDto;
import com.company.firstprojectspring.exaption.ContentNotFoundException;
import com.company.firstprojectspring.exaption.CustomExceptionHandler;
import com.company.firstprojectspring.module.Card;
import com.company.firstprojectspring.module.Users;
import com.company.firstprojectspring.repository.CardRepository;
import com.company.firstprojectspring.repository.UserRepository;
import com.company.firstprojectspring.service.UserService;
import com.company.firstprojectspring.service.mapper.UserMapper;
import com.company.firstprojectspring.service.validation.UserValidation;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {


    private Integer userId;
    @Getter
    private final List<Users> usersList;

    private final CustomExceptionHandler customExceptionHandler;
    private final UserMapper userMapper;
    private CardServiceImpl cardService;
    private UserValidation userValidation;
    private UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserValidation userValidation, UserRepository userRepository, CardServiceImpl cardService, CustomExceptionHandler customExceptionHandler) {
        this.userId = 0;
        this.userMapper = userMapper;
        this.cardService = cardService;
        this.usersList = new ArrayList<>();
        this.userValidation = userValidation;
        this.customExceptionHandler = customExceptionHandler;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse<UserDto> createUser(@RequestBody UserDto dto) {
//        List<ErrorDto> errors = this.userValidation.validation(dto);
//
////        if(errorList.size()>0){}
//        if (!errors.isEmpty()) {
//            return ApiResponse.<UserDto>builder()
//                    .code(-2)
//                    .message("Validation Failed")
//                    .errorList(errors)
//                    .build();
//
//        }

        Users users = this.userMapper.toEntity(dto);
//        users.setUserId(++this.userId);
//        usersList.add(users);
        Users savedUsers = this.userRepository.save(users);

        return ApiResponse.<UserDto>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDto(savedUsers))
                .build();
    }

    @Override
    public ApiResponse<UserDto> getUserById(@RequestBody Integer userId) {

//        for (Users users : this.usersList) {
//            if (users.getUserId().equals(userId)) {
//
//                return ApiResponse.<UserDto>builder()
//                        .success(true)
//                        .message("OK")
//                        .content(this.userMapper.toDtoWithAllEntity(users))
//                        .build();
//            }
//        }


        Users users = this.userRepository.findByUserId(userId);
        if (users == null) {


            throw new ContentNotFoundException(String.format("This user %d id is not found!", userId));


        }
        return ApiResponse.<UserDto>builder()
                .success(true)
                .message("OK")
                .content(userMapper.toDto(users)).build();



    }

//    @Override
//    public ApiResponse<UserDto> updateUserById(
//                                @RequestParam(value = "id") Integer userId,
//                                @RequestBody UserDto dto) {
//        for(Users users : this.usersList){
//            if(users.getUserId().equals(userId)){
//
//                int index = this.usersList.indexOf(users);
//
//                Users updatedUser = this.userMapper.updateUserAllFields(users, dto);
//                this.usersList.set(index, updatedUser);
//
//
//                return ApiResponse.<UserDto>builder()
//                        .success(true)
//                        .message("OK")
//                        .content(this.userMapper.toDtoWithCard(users))
//                        .build();
//
//            }
//        }
//        return ApiResponse.<UserDto>builder()
//                .code(-1)
//                .message(String.format("This user %d id is not found!", userId))
//                .build();
//    }


    @Override
    public ApiResponse<UserDto> updateUserById(
            @RequestParam(value = "id") Integer userId,
            @RequestBody UserDto dto) {
        for (Users users : this.usersList) {
            if (users.getUserId().equals(userId)) {

                int index = this.usersList.indexOf(users);
//                List<Card> cardList = this.usersList.get(index).getCardList();
                List<Card> cards = this.cardService.cardList;
                Users updatedUser = this.userMapper.updateUserAllFields(users, dto);
                this.userMapper.updateHolderName(cards, updatedUser, userId);
                this.usersList.set(index, updatedUser);


                return ApiResponse.<UserDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.userMapper.toDtoWithAllEntity(users))
                        .build();

            }
        }
        throw new ContentNotFoundException(String.format("This user %d id is not found!", userId));

    }

    @Override
    public ApiResponse<UserDto> deleteUserById(@RequestParam(value = "id") Integer userId) {
        for (Users users : this.usersList) {
            if (users.getUserId().equals(userId)) {
                this.usersList.remove(users);
                return ApiResponse.<UserDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.userMapper.toDtoWithAllEntity(users))
                        .build();
            }
        }
        throw new ContentNotFoundException(String.format("This user %d id is not found!", userId));

    }

    @Override
    public ApiResponse<List<UserDto>> getAllUsers() {
        if (this.usersList.isEmpty()) {
            throw new ContentNotFoundException("User list is empty");
        }
        return ApiResponse.<List<UserDto>>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDtoList(this.usersList))
                .build();
    }

    public boolean existByUserId(Integer userId) {
        for (Users user : usersList) {
            if (user.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

}
