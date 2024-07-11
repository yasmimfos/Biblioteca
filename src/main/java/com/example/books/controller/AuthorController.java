package com.example.books.controller;

import com.example.books.dtos.AuthorDto;
import com.example.books.models.Author;
import com.example.books.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<Author> registerAuthor(@RequestBody @Valid AuthorDto authorDto){
        var register = authorService.register(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllAuthors(){
        var get = authorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(get);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable(value = "id") Long id){
        var author = authorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(author);

    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long id, @RequestBody @Valid AuthorDto authorDto){
        var author = authorService.update(id, authorDto);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") Long id){
        var author = authorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(author);

    }
}
