package com.example.books.services;


import com.example.books.dtos.AuthorDto;
import com.example.books.models.Author;
import com.example.books.repositories.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Object getAll(){
        return authorRepository.findAll();
    }

    public Object register(AuthorDto authorDto){
        var author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        authorRepository.save(author);
        return author;
    }

    public Object getById(Long id){
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()){
            return "Author not found";
        }
        return author;
    }

    public Object update(Long id, AuthorDto authorDto){
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()){
            return "Author not found";
        }
        var authorModel = author.get();
        BeanUtils.copyProperties(authorDto, authorModel);
        authorRepository.save(authorModel);
        return authorModel;

    }

    public Object delete(Long id){
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()){
            return "Author not found";
        }
        authorRepository.delete(author.get());
        return "deleted";
    }
}
