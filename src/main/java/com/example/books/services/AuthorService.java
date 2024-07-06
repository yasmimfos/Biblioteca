package com.example.books.services;


import com.example.books.dtos.AuthorDto;
import com.example.books.dtos.AuthorDtoSave;
import com.example.books.models.Author;
import com.example.books.models.Publishing;
import com.example.books.repositories.AuthorRepository;
import com.example.books.repositories.PublishingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PublishingRepository publishingRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, PublishingRepository publishingRepository) {
        this.authorRepository = authorRepository;
        this.publishingRepository = publishingRepository;
    }

    public Object getAll(){
        return authorRepository.findAll();
    }

    public Object register(AuthorDtoSave authorDtoSave){
        Optional<Publishing> company = publishingRepository.findByCompany(authorDtoSave.publishingCompany());
        if (company.isEmpty()){
            return "The company wasn't found";
        }

        var author = new Author(authorDtoSave.name(), company.get().getId_pub());
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

    public Object update(Long id, AuthorDtoSave authorDtoSave){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()){
            return "Author not found";
        }

        Optional<Publishing> company = publishingRepository.findByCompany(authorDtoSave.publishingCompany());
        if (company.isEmpty()){
            return "The company wasn't found";
        }

        var authorModel = new Author(authorDtoSave.name(), company.get().getId_pub());
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
