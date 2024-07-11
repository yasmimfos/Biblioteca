package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookDto(@NotBlank String title,
                      @NotBlank String author,
                      int ageRange,
                      @NotNull int pages,
                      Date release,
                      @NotBlank String genre) {

}
