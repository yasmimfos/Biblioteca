package com.example.books.repositories;

import com.example.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {


}
