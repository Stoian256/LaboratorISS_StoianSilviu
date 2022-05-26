package com.example.proiectbibliotecaiss.business;

import com.example.proiectbibliotecaiss.domain.Book;
import com.example.proiectbibliotecaiss.domain.BookStatus;
import com.example.proiectbibliotecaiss.repository.BookDbRepository;
import javafx.scene.control.Alert;

import java.util.Collection;
import java.util.List;

public class BookService {
    private final BookDbRepository bookDbRepository;

    public BookService(BookDbRepository bookDbRepository) {
        this.bookDbRepository = bookDbRepository;
    }
    public Collection<Book> findAllBooks(){
        return bookDbRepository.findAll();
    }
    public Collection<Book> findBorrowedBooksBySubscriber(int subscriberId){
        return bookDbRepository.findBorrowedBooksBySubscriberIdAll(subscriberId);
    }

    public void borrowBooks(int subscriberID,List<Integer> booksId){
        for(int bookId :booksId){
            Book currentBook=bookDbRepository.findOne(bookId);
            if(currentBook.getStatus()==BookStatus.AVAILABLE) {
                bookDbRepository.update(new Book(bookId, null, null, null, 0, BookStatus.BORROWED));
                bookDbRepository.updateBorrowedBook(subscriberID,bookId);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Book ISBN:"+currentBook.getISBN());
                alert.setContentText("The book with title:"+currentBook.getTitle()+" is not available!");
                alert.showAndWait();
            }

        }
    }

    public void returnBooks(int subscriberID,List<Integer> booksId){
        for(int bookId :booksId){
            Book currentBook=bookDbRepository.findOne(bookId);
            if(currentBook.getStatus()==BookStatus.BORROWED) {
                bookDbRepository.update(new Book(bookId, null, null, null, 0, BookStatus.AVAILABLE));
                bookDbRepository.returnBook(subscriberID,bookId);
            }
        }
    }
}
