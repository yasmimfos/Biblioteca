package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;


public record AuthorDtoSave(
        @NotBlank String name,
        @NotBlank String publishingCompany) {
}
