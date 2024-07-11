package com.example.books.controller;

import com.example.books.dtos.BookDto;
import com.example.books.models.Book;
import com.example.books.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto bookDto){
        var book = bookService.register(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        var books = bookService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") Long id){
        var book = bookService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping("/books/releases-upcoming")
    public ResponseEntity<List<Book>> GetReleases(){
        List<Book> books = bookService.getRelease();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/findCompany")
    public ResponseEntity<List<Book>> GetByPublishing(@RequestParam String company){
        List<Book> books = bookService.getByPublishing(company);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/findAuthor")
    public ResponseEntity<List<Book>> GetByAuthor(@RequestParam String author){
        List<Book> books = bookService.getByAuthor(author);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/findTitle")
    public ResponseEntity<Book> GetByTitle(@RequestParam String title){
        Book book = bookService.getByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping("/books/findGenre")
    public ResponseEntity<List<Book>> GetByGenre(@RequestParam String genre){
        List<Book> books = bookService.getByGenre(genre);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id, @RequestBody @Valid BookDto bookDto){
        var book = bookService.update(id, bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long id){
        var book = bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
}
