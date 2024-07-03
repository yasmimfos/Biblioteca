package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record BookRecordDto(
        @NotBlank String title,
        @NotNull int author,
        int classificacao,
        @NotNull int pages,
        Date release,
        @NotBlank String genre) {

}