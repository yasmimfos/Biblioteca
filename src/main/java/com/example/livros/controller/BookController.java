package com.example.livros.controller;

import com.example.livros.dtos.BookRecordDto;
import com.example.livros.models.BookModel;
import com.example.livros.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/books")
    public ResponseEntity<BookModel> createBook(@RequestBody @Valid BookRecordDto livroRecordDto){
        var bookModel = new BookModel();
        BeanUtils.copyProperties(livroRecordDto, bookModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(bookModel));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") UUID id){
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("livro não ecnontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(book.get());
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") UUID id, @RequestBody @Valid BookRecordDto livroRecordDto){
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("livro não ecnontrado");
        }

        var livroModel = book.get();
        BeanUtils.copyProperties(livroRecordDto, livroModel);
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(livroModel));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") UUID id){
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("livro não ecnontrado");
        }
        bookRepository.delete(book.get());
        return  ResponseEntity.status(HttpStatus.OK).body("livro deletado com sucesso");
    }
}
