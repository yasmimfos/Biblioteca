package com.example.books.controller;

import com.example.books.dtos.AuthorDto;
import com.example.books.dtos.AuthorDtoSave;
import com.example.books.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<Object> registerAuthor(@RequestBody @Valid AuthorDtoSave authorDtoSave){
        var register = authorService.register(authorDtoSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @GetMapping("/author")
    public ResponseEntity<Object> getAllAuthors(){
        var get = authorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(get);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable(value = "id") Long id){
        var author = authorService.getById(id);
        if (author.equals("Author not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(author);

    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") Long id, @RequestBody @Valid AuthorDtoSave authorDtoSave){
        var author = authorService.update(id, authorDtoSave);
        if (author.equals("Author not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") Long id){
        var author = authorService.delete(id);
        if (author.equals("Author not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(author);
        }
        return ResponseEntity.status(HttpStatus.OK).body(author);

    }
}
