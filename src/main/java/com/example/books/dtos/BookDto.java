package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookDto(@NotBlank String title,
                      @NotBlank String author,
                      int ageRange,
                      @NotNull int pages,
                      LocalDate release,
                      @NotBlank String genre) {

}
