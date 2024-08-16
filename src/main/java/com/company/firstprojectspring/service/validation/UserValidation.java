package com.company.firstprojectspring.service.validation;

import com.company.firstprojectspring.dto.ErrorDto;
import com.company.firstprojectspring.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {

    public List<ErrorDto> validation(UserDto dto){
        List<ErrorDto> errors = new ArrayList<>();
        if(dto.getFirstname() == null
                ||dto.getFirstname().trim().isEmpty()

        ) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setFieldname("firstname");
            errorDto.setMessage("Firstname cannot be empty");
            errors.add(errorDto);
        }
        if(dto.getLastname() == null
                ||dto.getLastname().trim().isEmpty()

        ) {
            errors.add(new ErrorDto("lastname","Lastname cannot be empty"));
        }
        if (dto.getEmail() == null
                ||dto.getEmail().trim().isEmpty()

        ){
            errors.add(new ErrorDto("email","Email cannot be empty"));
        }
        if (dto.getPassword() == null
                ||dto.getPassword().trim().isEmpty()

        ){
            errors.add(new ErrorDto("password","Password cannot be empty"));
        }
        if (dto.getAge()==null){
            errors.add(new ErrorDto("age","Age cannot be empty"));
        }


        return errors;

    }


}
