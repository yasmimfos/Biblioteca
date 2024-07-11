package com.example.books.models;

import com.example.books.dtos.BookDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_book;
    private String title;
    private Long author;
    private Long publishing;
    private int ageRange;
    private int pages;
    private LocalDate release;
    private String genre;


    public Book(BookDto data, Long idAuthor, Long idPub) {
        this.title = data.title();
        this.author = idAuthor;
        this.publishing = idPub;
        this.ageRange = data.ageRange();
        this.pages = data.pages();
        this.release = data.release();
        this.genre = data.genre();
    }

    public Book() {

    }
}
