package com.company.firstprojectspring.controller;


import com.company.firstprojectspring.dto.ApiResponse;

import com.company.firstprojectspring.dto.ProdDto;
import com.company.firstprojectspring.service.ProdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="product")
public class ProdController {

    private final ProdService prodService;

    public ProdController(ProdService prodService) {
        this.prodService = prodService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse<ProdDto> createProd(@RequestBody ProdDto dto) {

        return this.prodService.createProd(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse<ProdDto> getProdById(@RequestParam(value = "id") Integer id) {

        return this.prodService.getProdById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse<ProdDto> updateProd(@RequestParam(value = "id") Integer prodId,
                                           @RequestBody ProdDto dto) {

        return this.prodService.updateProdById(prodId, dto);

    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse<ProdDto> deleteProdById(@RequestParam(value = "id") Integer prodId) {

        return this.prodService.deleteProdById(prodId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-all")
    public ApiResponse<List<ProdDto>> getAllProdList() {
        return this.prodService.getAllProds();
    }


}
