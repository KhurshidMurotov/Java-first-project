package com.company.firstprojectspring.service;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.module.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {


    ApiResponse<CardDto> createCard(CardDto dto);
    ApiResponse<CardDto> getCardById(Integer cardId);
    ApiResponse<CardDto> updateCardById(Integer cardId, CardDto dto);
    ApiResponse<CardDto> deleteCardById(Integer cardId);
    ApiResponse<List<CardDto>> getAllCards();

}
