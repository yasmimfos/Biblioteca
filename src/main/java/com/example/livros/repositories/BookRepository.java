package com.example.livros.repositories;

import com.example.livros.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BookRepository extends JpaRepository<BookModel, UUID> {
}
