package com.company.firstprojectspring.service.mapper;

import com.company.firstprojectspring.dto.ProdDto;
import com.company.firstprojectspring.module.Prod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdMapper {



    public Prod toEntity(ProdDto prodDto) {
        Prod prod = new Prod();
        prod.setProdId(prodDto.getProdId());
        prod.setProdType(prodDto.getProdType());
        prod.setProdName(prodDto.getProdName());
        prod.setProdColor(prodDto.getProdColor());
        prod.setProdPrice(prodDto.getProdPrice());
        prod.setUserId(prodDto.getUserId());
        return prod;
    }


    public ProdDto toDto(Prod prod) {
        ProdDto prodDto = new ProdDto();
        prodDto.setUserId(prod.getUserId());
        prodDto.setProdId(prod.getProdId());
        prodDto.setProdType(prod.getProdType());
        prodDto.setProdName(prod.getProdName());
        prodDto.setProdOwner(prod.getProdName());
        prodDto.setProdColor(prod.getProdColor());
        prodDto.setProdPrice(prod.getProdPrice());
        return prodDto;
    }

    public Prod updateAllFieldsProd(Prod prod, ProdDto dto) {
        if (dto.getProdType() != null) {
            prod.setProdType(dto.getProdType());
        }

        if (dto.getProdName() != null) {
            prod.setProdName(dto.getProdName());
        }

        if (dto.getProdColor() != null) {
            prod.setProdColor(dto.getProdColor());
        }

        if (dto.getProdPrice() != null) {
            prod.setProdPrice(dto.getProdPrice());
        }

        if (dto.getProdOwner() != null){
            prod.setProdOwner(dto.getProdOwner());
        }

        if (dto.getUserId() != null) {
            prod.setUserId(dto.getUserId());
        }

        return prod;
    }

    public List<ProdDto> toDtoList(List<Prod> prodList) {
        List<ProdDto> prodDtoList = new ArrayList<>();
        for (Prod prod : prodList) {
            prodDtoList.add(toDto(prod));
        }
        return prodDtoList;
    }
}