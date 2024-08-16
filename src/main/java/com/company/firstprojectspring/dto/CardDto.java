package com.company.firstprojectspring.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private Integer cardId;


    private String holderName;
    @NotBlank(message = "CardType cant be empty")
    private String cardType;
    @NotBlank(message = "cardNumber cant be empty")
    private String cardNumber;
    @NotBlank(message = "cardCode cant be empty")
    private String cardCode;
    @NotBlank(message = "cardExpiryDate cant be empty")
    private String cardExpiryDate;

    private Integer userId;


}
