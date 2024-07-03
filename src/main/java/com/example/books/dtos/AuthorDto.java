package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthorDto(
        @NotBlank String name,
        @NotNull Long publishingCompany) {
}
