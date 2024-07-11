package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;


public record AuthorDto(
        @NotBlank String name,
        @NotBlank String publishingCompany) {
}
