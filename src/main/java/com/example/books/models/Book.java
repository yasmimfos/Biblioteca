package com.example.books.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long id_book;
    private String title;
    private Long author;
    private int classificacao;
    private int pages;
    private Date release;
    private String genre;


    public Book(String title, Long idAuthor, int classificacao, int pages, Date release, String genre) {
        this.title = title;
        this.author = idAuthor;
        this.classificacao = classificacao;
        this.pages = pages;
        this.release = release;
        this.genre = genre;
    }

    public Book() {

    }
}
