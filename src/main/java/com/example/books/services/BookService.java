package com.example.books.services;


import com.example.books.dtos.BookDto;
import com.example.books.dtos.BookDtoSave;
import com.example.books.exceptions.AlreadyExistsException;
import com.example.books.exceptions.NotFoundException;
import com.example.books.models.Author;
import com.example.books.models.Book;
import com.example.books.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book register(BookDto bookDto){
        Optional<Book> verification = bookRepository.findByTitle(bookDto.title());
        if(verification.isPresent()){
            throw new AlreadyExistsException("The book has already been registered");
        }

        Author author = authorService.ifAuthorExists(bookDto.author());

        Book book = new Book(bookDto, author.getId_author(), author.getPublishingCompany());
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()){
            throw new NotFoundException("Book not found");
        }
        return book.get();
    }

    public Book update(Long id, BookDto bookDto){
        Book book = getById(id);

        Author author = authorService.ifAuthorExists(bookDto.author());

        BookDtoSave bookDtoSave = new BookDtoSave(
                bookDto.title(),
                author.getId_author(),
                author.getPublishingCompany(),
                bookDto.ageRange(),
                bookDto.pages(),
                bookDto.release(),
                bookDto.genre());
        BeanUtils.copyProperties(bookDtoSave, book);
        bookRepository.save(book);
        return book;
    }

    public String delete(Long id){
        Book book = getById(id);
        bookRepository.delete(book);
        return "Deleted";
    }

}
