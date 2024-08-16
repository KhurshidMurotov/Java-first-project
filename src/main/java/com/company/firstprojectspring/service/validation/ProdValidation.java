package com.company.firstprojectspring.service.validation;


import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.dto.ErrorDto;
import com.company.firstprojectspring.dto.ProdDto;
import com.company.firstprojectspring.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdValidation {


    private final UserServiceImpl userService;

    public List<ErrorDto> validateProd(ProdDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if (dto.getProdType() == null
                ||dto.getProdType().trim().isEmpty()
        ){
            errors.add(new ErrorDto("prodType","ProdType cannot be empty"));
        }

        if (dto.getProdColor() == null
                ||dto.getProdColor().trim().isEmpty()
        ){
            errors.add(new ErrorDto("prodColor","ProdColor cannot be empty"));
        }
//        if (dto.getProdPrice() == null
//                ||dto.getProdPrice().trim().isEmpty()



        return errors;
    }







}
