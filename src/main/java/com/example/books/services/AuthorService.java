package com.example.books.services;


import com.example.books.dtos.AuthorDto;
import com.example.books.dtos.AuthorDtoSave;
import com.example.books.exceptions.AlreadyExistsException;
import com.example.books.exceptions.NotFoundException;
import com.example.books.models.Author;
import com.example.books.repositories.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final PublishingService publishingService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, PublishingService publishingService) {
        this.authorRepository = authorRepository;
        this.publishingService = publishingService;
    }

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public Author register(AuthorDto authorDto){
        Optional<Author> verification = authorRepository.findAuthorByName(authorDto.name());
        if(verification.isPresent()){
            throw new AlreadyExistsException("The author has already been registered");
        }

        Long company = publishingService.ifCompanyExists(authorDto.publishingCompany());

        var author = new Author(authorDto.name(), company);
        authorRepository.save(author);
        return author;
    }

    public Author getById(Long id){
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()){
            throw new NotFoundException("Author not found");
        }
        return author.get();
    }

    public Author update(Long id, AuthorDto authorDto){
        Author author = getById(id);
        Long company = publishingService.ifCompanyExists(authorDto.publishingCompany());

        AuthorDtoSave authorDtoSave = new AuthorDtoSave(authorDto.name(), company);
        BeanUtils.copyProperties(authorDtoSave, author);
        authorRepository.save(author);
        return author;
    }

    public String delete(Long id){
        Author author = getById(id);
        authorRepository.delete(author);
        return "deleted";
    }

    public Author ifAuthorExists(String name){
        Optional<Author> author = authorRepository.findAuthorByName(name);
        if (author.isEmpty()){
            throw new NotFoundException("Author not found");
        } else{
            return author.get();
        }
    }
}
