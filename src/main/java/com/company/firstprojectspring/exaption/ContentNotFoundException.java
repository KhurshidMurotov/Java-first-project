package com.company.firstprojectspring.exaption;

public class ContentNotFoundException extends RuntimeException{
    public ContentNotFoundException(String message) {
        super(message);
    }
}


