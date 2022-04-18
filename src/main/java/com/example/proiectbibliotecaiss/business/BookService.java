package com.example.proiectbibliotecaiss.business;

import com.example.proiectbibliotecaiss.domain.Book;
import com.example.proiectbibliotecaiss.repository.BookDbRepository;
import java.util.Collection;

public class BookService {
    private final BookDbRepository bookDbRepository;

    public BookService(BookDbRepository bookDbRepository) {
        this.bookDbRepository = bookDbRepository;
    }
    public Collection<Book> findAllBooks(){
        return bookDbRepository.findAll();
    }
}
