package com.company.firstprojectspring.service.mapper;


import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.module.Card;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CardMapper {


    @Mapping(target = "cardId", ignore = true)
    public abstract Card toEntity(CardDto cardDto) ;/*{
        Card card = new Card();
        card.setCardId(cardDto.getCardId());
        card.setCardType(cardDto.getCardType());
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardCode(cardDto.getCardCode());
        card.setCardExpiryDate(cardDto.getCardExpiryDate());
        card.setUserId(cardDto.getUserId());
        return card;
        }
   */

    public abstract CardDto toDto(Card card) ;/*{
        CardDto cardDto = new CardDto();
        cardDto.setCardId(card.getCardId());
        cardDto.setUserId(card.getUserId());
        cardDto.setCardType(card.getCardType());
        cardDto.setCardCode(card.getCardCode());
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setHolderName(card.getHolderName());
        cardDto.setCardExpiryDate(card.getCardExpiryDate());
        return cardDto;
    }*/

    public abstract List<CardDto> toDtoList(List<Card> cardList) ;/*{
        List<CardDto> cardDtoList = new ArrayList<>();
        for (Card card : cardList) {
            cardDtoList.add(toDto(card));
        }
        return cardDtoList;
    }*/


    @Mapping(target = "cardId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Card updateAllFieldsCard(@MappingTarget Card card, CardDto dto);
//    {
//
//
//        if (dto.getHolderName() != null){
//            card.setHolderName(dto.getHolderName());
//
//        }
//        if (dto.getCardType() != null) {
//            card.setCardType(dto.getCardType());
//        }
//
//        if (dto.getCardNumber() != null) {
//            card.setCardNumber(dto.getCardNumber());
//        }
//
//        if (dto.getCardCode() != null) {
//            card.setCardCode(dto.getCardCode());
//        }
//
//        if (dto.getUserId() != null) {
//            card.setUserId(dto.getUserId());
//        }
//
//        if (dto.getCardExpiryDate() != null) {
//            card.setCardExpiryDate(dto.getCardExpiryDate());
//        }
//
//        return card;
//    }
}
