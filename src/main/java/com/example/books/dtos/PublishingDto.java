package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;

public record PublishingDto(
        @NotBlank String company) {
}
