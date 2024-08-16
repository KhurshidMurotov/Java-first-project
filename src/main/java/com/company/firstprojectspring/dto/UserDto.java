package com.company.firstprojectspring.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;

    @NotBlank(message = "Firstname cant be empty")
    private String firstname;
    @NotBlank(message = "lastname cant be empty")
    private String lastname;
    @Email(message = "The given email is incorrect")
    @NotBlank(message = "Email cant be empty")
    private String email;
    @NotBlank(message = "password cant be empty")
    private String password;
//    @NotBlank(message = "Age cant be null")
    private LocalDate age;

    private List<CardDto> cardList;
    private List<ProdDto> prodList;



}
