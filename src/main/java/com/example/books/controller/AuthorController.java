package com.example.books.controller;

import com.example.books.dtos.AuthorDto;
import com.example.books.models.Author;
import com.example.books.repositories.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/author")
    public ResponseEntity<Object> registerAuthor(@RequestBody @Valid AuthorDto authorDto){
        var author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        return ResponseEntity.status(HttpStatus.CREATED).body( authorRepository.save(author));
    }

    @GetMapping("/author")
    public ResponseEntity<Object> getAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.findAll());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable(value = "id") Long id){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(author.get());
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") Long id, @RequestBody @Valid AuthorDto authorDto){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        var authorModel = author.get();
        BeanUtils.copyProperties(authorDto, authorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body( authorRepository.save(authorModel));
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") Long id){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        authorRepository.delete(author.get());
        return ResponseEntity.status(HttpStatus.OK).body("Author deleted successfully");

    }
}
