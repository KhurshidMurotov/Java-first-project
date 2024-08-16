package com.company.firstprojectspring.service.validation;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.dto.ErrorDto;
import com.company.firstprojectspring.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
@RequiredArgsConstructor
public class CardValidation {


    private final UserServiceImpl userService;

    public List<ErrorDto> validateCard(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();




        if (dto.getCardType() == null
                ||dto.getCardType().trim().isEmpty()
        ) {
            errors.add(new ErrorDto("cardType", "cardType cannot be empty"));
        }
        if (dto.getCardNumber() == null
                || dto.getCardNumber().trim().isEmpty()
                || isDigit(dto.getCardNumber())
                || dto.getCardNumber().length() != 4
        ) {
            errors.add(new ErrorDto("cardNumber", "Failed to add card number"));
        }
        if (dto.getCardCode() == null
                || dto.getCardCode().trim().isEmpty()
                || isDigit(dto.getCardCode())
                || dto.getCardCode().length() != 4
        ) {
            errors.add(new ErrorDto("cardCode", "CardCode cannot be empty"));
        }
        if (dto.getUserId() == null) {
            errors.add(new ErrorDto("userId", "UserId cannot be null"));
        }else {
            if (!this.userService.existByUserId(dto.getUserId())) {
                errors.add(new ErrorDto("userId",String.format("User %d id is not exists", dto.getUserId())));
            }
        }
        if (dto.getCardExpiryDate() == null) {
            errors.add(new ErrorDto("cardExpiryDate", "CardExpiryDate cannot be empty"));
        }



        return errors;
    }



    private boolean isDigit(String cardValue){
        for (char value : cardValue.toCharArray()){
            if (!Character.isDigit(value)){
                return true;
            }
        }
        return false;
    }


    public List<ErrorDto> validateCardWithUserId(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (dto.getUserId() != null) {
            if (!this.userService.existByUserId(dto.getUserId())) {
                errors.add(new ErrorDto("userId",String.format("User %d id is not found", dto.getUserId())));

            }
        }
        return errors;
    }

}
