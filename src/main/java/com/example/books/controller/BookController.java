package com.example.books.controller;

import com.example.books.dtos.BookDtoSave;
import com.example.books.dtos.BookRecordDto;
import com.example.books.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Object> createBook(@RequestBody @Valid BookDtoSave bookDtoSave){
        var book = bookService.register(bookDtoSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/books")
    public ResponseEntity<Object> getAllBooks(){
        var books = bookService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") Long id){
        var book = bookService.getById(id);
        if (book.equals("Book not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody @Valid BookDtoSave bookDtoSave){
        var book = bookService.update(id, bookDtoSave);
        if (book.equals("Book not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long id){
        var book = bookService.delete(id);
        if (book.equals("Book not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
}
