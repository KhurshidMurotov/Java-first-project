package com.company.firstprojectspring.service;

import com.company.firstprojectspring.dto.ApiResponse;
import com.company.firstprojectspring.dto.ProdDto;

import java.util.List;

public interface ProdService {
    ApiResponse<ProdDto> createProd(ProdDto dto);
    ApiResponse<ProdDto> getProdById(Integer prodId);
    ApiResponse<ProdDto> updateProdById(Integer prodId, ProdDto dto);
    ApiResponse<ProdDto> deleteProdById(Integer prodId);
    ApiResponse<List<ProdDto>> getAllProds();


}
