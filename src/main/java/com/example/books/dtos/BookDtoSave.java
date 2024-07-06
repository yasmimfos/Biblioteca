package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookDtoSave(@NotBlank String title,
                          @NotBlank String author,
                          int classificacao,
                          @NotNull int pages,
                          Date release,
                          @NotBlank String genre) {
}
