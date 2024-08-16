package com.company.firstprojectspring.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private int code;
    private T content;

    private List<ErrorDto> errorList;

}
