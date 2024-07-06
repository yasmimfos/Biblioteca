package com.example.books.services;


import com.example.books.dtos.BookDtoSave;
import com.example.books.models.Author;
import com.example.books.models.Book;
import com.example.books.repositories.AuthorRepository;
import com.example.books.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, AuthorRepository authorRepository1) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository1;
    }

    public Object register(BookDtoSave bookDtoSave){
        Optional<Author> author = authorRepository.findAuthorByName(bookDtoSave.author());
        if (author.isEmpty()){
            return "Author not found";
        }

        var book = new Book(bookDtoSave.title(),
                            author.get().getId_author(),
                            bookDtoSave.classificacao(),
                            bookDtoSave.pages(),
                            bookDtoSave.release(),
                            bookDtoSave.genre());
        bookRepository.save(book);
        return book;
    }

    public Object getAll(){
        return bookRepository.findAll();
    }

    public Object getById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()){
            return "Book not found";
        }
        return book;
    }

    public Object update(Long id, BookDtoSave bookDtoSave){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()){
            return "Book not found";
        }

        Optional<Author> author = authorRepository.findAuthorByName(bookDtoSave.author());
        if (author.isEmpty()){
            return "Author not found";
        }

        var bookModel = new Book(bookDtoSave.title(),
                author.get().getId_author(),
                bookDtoSave.classificacao(),
                bookDtoSave.pages(),
                bookDtoSave.release(),
                bookDtoSave.genre());
        bookRepository.save(bookModel);
        return bookModel;
    }

    public Object delete(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()){
            return "Book not found";
        }
        bookRepository.delete(book.get());
        return "Deleted";
    }

}
