package com.company.firstprojectspring.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdDto {
    private Integer prodId;
    private String prodOwner;
    private String prodName;
    private String prodType;
    private String prodColor;
    private String prodPrice;

    private Integer userId;


}
