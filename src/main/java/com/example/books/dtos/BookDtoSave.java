package com.example.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.Date;


public record BookDtoSave(
        @NotBlank String title,
        @NotNull Long author,
        @NotBlank Long publishing,
        int ageRange,
        @NotNull int pages,
        Date release,
        @NotBlank String genre) {



    public BookDtoSave(String title, Long author, Long publishing, int ageRange, int pages, Date release, String genre) {
        this.title = title;
        this.author = author;
        this.publishing = publishing;
        this.ageRange = ageRange;
        this.pages = pages;
        this.release = release;
        this.genre = genre;
    }


}
