package com.example.proiectbibliotecaiss.controllers;

import com.example.proiectbibliotecaiss.business.BookService;
import com.example.proiectbibliotecaiss.business.LibrarianSerivce;
import com.example.proiectbibliotecaiss.business.SubscriberService;
import com.example.proiectbibliotecaiss.domain.Book;
import com.example.proiectbibliotecaiss.domain.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriberController {
    private BookService bookService;
    private LibrarianSerivce librarianSerivce;
    private SubscriberService subscriberService;
    private Subscriber currentSubscriber;

    @FXML
    Button buttonUpdataBookList;
    @FXML
    TableView<Book> tableViewAvailableBooks;
    @FXML
    TableColumn<Book, Integer> columnBookId;
    @FXML
    TableColumn<Book, String> columnBookIsbn;
    @FXML
    TableColumn<Book, String> columnBookTitle;
    @FXML
    TableColumn<Book, String> columnBookAuthor;
    @FXML
    TableColumn<Book, Integer> columnBookYear;
    ObservableList<Book> modelBooks = FXCollections.observableArrayList();

    public void setSubscriber(Subscriber subscriber) {
        this.currentSubscriber = subscriber;
    }

    public void setServices(LibrarianSerivce librarianSerivce, SubscriberService subscriberService, BookService bookService) {
        this.librarianSerivce = librarianSerivce;
        this.subscriberService = subscriberService;
        this.bookService = bookService;
        initModel();
    }


    public void initialize() {
        columnBookId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookID"));
        columnBookIsbn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        columnBookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        columnBookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        columnBookYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        tableViewAvailableBooks.setItems(modelBooks);
    }

    private void initModel() {
        initModelBooks();
    }


    @FXML
    private void initModelBooks() {
        if (currentSubscriber != null) {
            List<Book> bookList = bookService.findAllBooks().stream().filter(book->book.getStatus().toString()=="AVAILABLE") .collect(Collectors.toList());
            modelBooks.setAll(bookList);
        }
    }

    @FXML
    private void handleButtonUpdataBookListClicked(){
        List<Book> bookList = bookService.findAllBooks().stream().filter(book->book.getStatus().toString()=="AVAILABLE").collect(Collectors.toList());
        modelBooks.setAll(bookList);
    }
}
