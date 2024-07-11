package com.example.books.repositories;

import com.example.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    List<Book> findBookByReleaseAfter(LocalDate today);

    List<Book> getByPublishing(Long company);

    List<Book> getByAuthor(Long idAuthor);

    List<Book> getByGenre(String genre);
}
