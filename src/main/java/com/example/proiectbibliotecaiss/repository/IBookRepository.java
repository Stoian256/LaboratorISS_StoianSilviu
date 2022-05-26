package com.example.proiectbibliotecaiss.repository;

import com.example.proiectbibliotecaiss.domain.Book;

import java.util.Collection;
import java.util.List;

public interface IBookRepository extends IRepository<Book>{
    Collection<Book> findBorrowedBooksBySubscriberIdAll(int subscriberId);
    void updateBorrowedBook(int subscriberID,int bookId);
    void returnBook(int subscriberID,int bookId);
}
