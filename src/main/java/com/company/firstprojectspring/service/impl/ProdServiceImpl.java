package com.company.firstprojectspring.service.impl;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.ProdDto;
import com.company.firstprojectspring.dto.UserDto;
import com.company.firstprojectspring.module.Prod;
import com.company.firstprojectspring.module.Users;
import com.company.firstprojectspring.service.ProdService;
import com.company.firstprojectspring.service.mapper.ProdMapper;
import com.company.firstprojectspring.service.mapper.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public  class ProdServiceImpl implements ProdService {

    private Integer prodId;
    private List<Prod> prodList;

    private UserMapper userMapper;

    private ProdMapper prodMapper;

    private  UserServiceImpl userService;


    public ProdServiceImpl (
            ProdMapper prodMapper,
            @Lazy UserServiceImpl userService,
            UserMapper userMapper
    ) {
        this.prodId = 0;
        this.prodMapper = prodMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.prodList = new ArrayList<>();
    }




    @Override
    public ApiResponse<ProdDto> createProd(ProdDto dto) {

        if (!this.userService.existByUserId(dto.getUserId())) {
            return ApiResponse.<ProdDto>builder()
                    .code(-1)
                    .message(String.format("User %d is not found ",dto.getUserId())).build();

        }
        Prod prod = this.prodMapper.toEntity(dto);
        ApiResponse<UserDto> apiResponse = this.userService.getUserById(prod.getUserId());
        Users users = this.userMapper.toEntity(apiResponse.getContent());
        prod.setProdOwner(String.format("%s %s", users.getFirstname(), users.getLastname()));

        prod.setProdId(++this.prodId);
        this.prodList.add(prod);
        return ApiResponse.<ProdDto>builder()
                .success(true)
                .message("OK")
                .content(this.prodMapper.toDto(prod)).build();
    }


    @Override
    public ApiResponse<ProdDto> getProdById(Integer prodId) {
        for (Prod prod : prodList) {
            if (prod.getProdId().equals(prodId)) {
                return ApiResponse.<ProdDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.prodMapper.toDto(prod)).build();
            }
        }
        return ApiResponse.<ProdDto>builder()
                .code(-1)
                .message(String.format("User %d is not found ",prodId)).build();
    }


    @Override
    public ApiResponse<ProdDto> updateProdById(Integer prodId, ProdDto dto) {

        if (dto.getUserId() != null) {
            if (!this.userService.existByUserId(dto.getUserId())) {
                return null;
            }
        }
        for (Prod prod : prodList) {
            if (prod.getProdId().equals(prodId)) {

                int index = this.prodList.indexOf(prod);

                Prod updatedProd = this.prodMapper.updateAllFieldsProd(prod, dto);

                this.prodList.set(index, updatedProd);

                return ApiResponse.<ProdDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.prodMapper.toDto(updatedProd)).build();


            }
        }
        return ApiResponse.<ProdDto>builder()
                .code(-1)
                .message(String.format("User %d is not found ",prodId)).build();
    }

    @Override
    public ApiResponse<ProdDto> deleteProdById(Integer prodId) {

        for (Prod prod : prodList) {
            if (prod.getProdId().equals(prodId)) {
                this.prodList.remove(prod);
                return ApiResponse.<ProdDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.prodMapper.toDto(prod)).build();
            }
        }

        return ApiResponse.<ProdDto>builder()
                .code(-1)
                .message(String.format("User %d is not found ",prodId)).build();
    }

    @Override
    public ApiResponse<List<ProdDto>> getAllProds() {
        if (!prodList.isEmpty()) {

            return ApiResponse.<List<ProdDto>>builder()
                    .code(-1)
                    .message("Prod list is empty")
                    .build();
        }
        return ApiResponse.<List<ProdDto>>builder()
                .success(true)
                .message("OK")
                .content(this.prodMapper.toDtoList(this.prodList))
                .build();
    }



    public List<ProdDto> getAllProdsByUserId(Integer userId) {
        List<ProdDto> prods = new ArrayList<>();
        for (Prod prod : prodList) {
            if (prod.getUserId().equals(userId)) {
                prods.add(this.prodMapper.toDto(prod));
            }
        }
        return prods;
    }
}
