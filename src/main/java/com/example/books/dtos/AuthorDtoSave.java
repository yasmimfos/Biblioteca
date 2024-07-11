package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthorDtoSave(
        @NotBlank String name,
        @NotNull Long publishingCompany) {

    public AuthorDtoSave(String name, Long publishingCompany){
        this.name = name;
        this.publishingCompany = publishingCompany;

    }
}
