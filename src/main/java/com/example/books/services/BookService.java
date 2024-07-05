package com.example.books.services;


import com.example.books.dtos.BookRecordDto;
import com.example.books.models.Book;
import com.example.books.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Object register(BookRecordDto bookRecordDto){
        var book = new Book();
        BeanUtils.copyProperties(bookRecordDto, book);
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

    public Object update(Long id, BookRecordDto bookRecordDto){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()){
            return "Book not found";
        }
        var bookModel = book.get();
        BeanUtils.copyProperties(bookRecordDto, bookModel);
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
