package com.company.firstprojectspring.service.impl;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.dto.ErrorDto;
import com.company.firstprojectspring.dto.UserDto;
import com.company.firstprojectspring.module.Card;
import com.company.firstprojectspring.module.Users;
import com.company.firstprojectspring.repository.CardRepository;
import com.company.firstprojectspring.service.CardService;
import com.company.firstprojectspring.service.mapper.CardMapper;
import com.company.firstprojectspring.service.mapper.UserMapper;
import com.company.firstprojectspring.service.validation.CardValidation;
import com.company.firstprojectspring.service.validation.UserValidation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardServiceImpl implements CardService {

    private Integer cardId;

    @Getter
    protected final List<Card> cardList;

    private final UserMapper userMapper;
    private  final CardMapper cardMapper;
    private  final UserServiceImpl userService;
    private final CardValidation cardValidation;
    private final CardRepository cardRepository;


    public CardServiceImpl(
            CardMapper cardMapper,
            @Lazy UserServiceImpl userService,
            CardRepository cardRepository,
            UserMapper userMapper,
            @Lazy CardValidation cardValidation
    ) {
        this.cardId = 0;
        this.cardMapper = cardMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.cardList = new ArrayList<>();
        this.cardValidation = cardValidation;
        this.cardRepository=cardRepository;
    }





    @Override
    public ApiResponse<CardDto> createCard(CardDto dto) {
//        if (!this.userService.existByUserId(dto.getUserId())) {
//            return ApiResponse.<CardDto>builder()
//                    .code(-1)
//                    .message(String.format("User %d is not found ",dto.getUserId())).build();
//
//        }

//        List<ErrorDto> errors = this.cardValidation.validateCard(dto);
//
////        if(errorList.size()>0){}
//
//
//
//
//        if (!errors.isEmpty()) {
//            return ApiResponse.<CardDto>builder()
//                    .code(-2)
//                    .message("Validation Failed")
//                    .errorList(errors)
//                    .build();
//
//        }
//
//        Card card = this.cardMapper.toEntity(dto);
//        ApiResponse<UserDto> apiResponse = this.userService.getUserById(card.getUserId());
//        Users users = this.userMapper.toEntity(apiResponse.getContent());
//        card.setHolderName(String.format("%s %s", users.getFirstname(), users.getLastname()));

//        card.setCardId(++this.cardId);
//        this.cardList.add(card);


        Card card = this.cardMapper.toEntity(dto);
        Card savedCard = this.cardRepository.save(card);
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(savedCard)).build();
    }


    @Override
    public ApiResponse<CardDto> getCardById(Integer cardId) {
//        for (Card card : cardList) {
//            if (card.getCardId().equals(cardId)) {
//                return ApiResponse.<CardDto>builder()
//                        .success(true)
//                        .message("OK")
//                        .content(this.cardMapper.toDto(card)).build();
//            }
//        }
        Card card = this.cardRepository.findByCardId(cardId);
        if (card == null) {
            return ApiResponse.<CardDto>builder()
                    .code(-1)
                    .message(String.format("Card %d is not found ",cardId)).build();
        }
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(card)).build();

    }


    @Override
    public ApiResponse<CardDto> updateCardById(Integer cardId, CardDto dto) {

//        List<ErrorDto> errors = this.cardValidation.validateCardWithUserId(dto);
//        if (!errors.isEmpty()) {
//            return ApiResponse.<CardDto>builder()
//                    .code(-2)
//                    .message("Validation Failed")
//                    .errorList(errors)
//                    .build();
//        }

//        for (Card oldCard : cardList) {
//            if (oldCard.getCardId().equals(cardId)) {
//
//                int index = this.cardList.indexOf(oldCard);
//
//
//
//                Card updatedCard = this.cardMapper.updateAllFieldsCard(oldCard, dto);
//
//
//                this.cardList.set(index, updatedCard);
//
//
//
//                return ApiResponse.<CardDto>builder()
//                        .success(true)
//                        .message("OK")
//                        .content(this.cardMapper.toDto(updatedCard)).build();
//
//
//            }
//        }

        Card card = this.cardRepository.findByCardId(cardId);
        if (card == null) {

            return ApiResponse.<CardDto>builder()
                    .code(-1)
                    .message(String.format("Card %d is not found ",cardId)).build();
        }

        Card updatedCard = this.cardMapper.updateAllFieldsCard(card, dto);
        Card savedCard = this.cardRepository.save(updatedCard);

        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(savedCard)).build();


    }

    @Override
    public ApiResponse<CardDto> deleteCardById(Integer cardId) {

//        for (Card card : this.cardList) {
//            if (card.getCardId().equals(cardId)) {
//                this.cardList.remove(card);
//                return ApiResponse.<CardDto>builder()
//                        .success(true)
//                        .message("OK")
//                        .content(this.cardMapper.toDto(card)).build();
//            }
//        }

        Card card = this.cardRepository.findByCardId(cardId);
        if (card == null) {

            return ApiResponse.<CardDto>builder()
                    .code(-1)
                    .message(String.format("Card %d is not found ",cardId)).build();
        }
        this.cardRepository.delete(card);

        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(card))
                .build();
    }

    @Override
    public ApiResponse<List<CardDto>> getAllCards() {
        if (!cardList.isEmpty()) {

            return ApiResponse.<List<CardDto>>builder()
                    .code(-1)
                    .message("Card list is empty")
                    .build();
        }
        return ApiResponse.<List<CardDto>>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDtoList(this.cardList))


                .build();
    }



    public List<CardDto> getAllCardsByUserId(Integer userId) {
        List<CardDto> cards = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getUserId().equals(userId)) {
                cards.add(this.cardMapper.toDto(card));
            }
        }
        return cards;
    }
}
