package com.company.firstprojectspring.controller;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.CardDto;
import com.company.firstprojectspring.module.Card;
import com.company.firstprojectspring.service.CardService;
import com.company.firstprojectspring.service.impl.CardServiceImpl;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value="card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService){
        this.cardService= cardService;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ApiResponse<CardDto> createCard(@RequestBody CardDto dto){

        return this.cardService.createCard(dto);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ApiResponse<CardDto> getCardById(@RequestParam(value = "id") Integer id){

        return this.cardService.getCardById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse<CardDto> updateCard(@RequestParam(value = "id") Integer cardId,
                           @RequestBody CardDto dto){

        return this.cardService.updateCardById(cardId, dto);

    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse<CardDto> deleteCardById(@RequestParam(value = "id") Integer cardId){

        return this.cardService.deleteCardById(cardId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-all")
    public ApiResponse<List<CardDto>> getAllCardList(){
        return this.cardService.getAllCards();
    }


}
