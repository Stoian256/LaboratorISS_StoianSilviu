package com.example.proiectbibliotecaiss.controllers;

import com.example.proiectbibliotecaiss.business.BookService;
import com.example.proiectbibliotecaiss.business.LibrarianSerivce;
import com.example.proiectbibliotecaiss.business.SubscriberService;
import com.example.proiectbibliotecaiss.domain.Book;
import com.example.proiectbibliotecaiss.domain.Librarian;
import com.example.proiectbibliotecaiss.domain.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class LibrarianController {

    private BookService bookService;
    private LibrarianSerivce librarianSerivce;
    private SubscriberService subscriberService;
    private Librarian currentLibrarian;

    @FXML
    TextField textFieldSearchCNP;
    @FXML
    Button buttonShowBorrowed;
    @FXML
    Button buttonReturnBook;
    @FXML
    TableView<Book> tableViewBooks;
    @FXML
    TableColumn<Book, Integer>tableColumnBookId;
    @FXML
    TableColumn<Book, String> tableColumnISBN;
    @FXML
    TableColumn<Book, String> tableColumnTitlu;
    @FXML
    TableColumn<Book, String> tableColumnAutor;
    @FXML
    TableColumn<Book, Integer> tableColumnAn;
    ObservableList<Book> modelBooks = FXCollections.observableArrayList();

    public void setLibrarian(Librarian librarian) {
        this.currentLibrarian = librarian;
    }

    public void setServices(LibrarianSerivce librarianSerivce,SubscriberService subscriberService,BookService bookService) {
        this.librarianSerivce=librarianSerivce;
        this.subscriberService=subscriberService;
        this.bookService=bookService;
        initModel();
    }


    public void initialize() {
        tableColumnBookId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookID"));
        tableColumnISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        tableColumnTitlu.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        tableColumnAn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        tableViewBooks.setItems(modelBooks);
        tableViewBooks.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
    }

    private void initModel() {
        initModelBorrowedBooks();
    }
    public void initModelBorrowedBooks(){
        Subscriber subscriber =subscriberService.findSubscriberByUsername(textFieldSearchCNP.getText());
        if (textFieldSearchCNP.getText() != ""&& subscriber!=null) {
            List<Book> bookList = bookService.findBorrowedBooksBySubscriber(subscriber.getSubscriberID()).stream().toList();
            modelBooks.setAll(bookList);
        }else modelBooks.clear();
    }

    @FXML
    public void handleButtonShowBooksClicked(){
        initModelBorrowedBooks();
    }

    @FXML
    public void handleReturnBookClicked(){
        List<Integer> booksId = new ArrayList<>();
        Subscriber subscriber =subscriberService.findSubscriberByUsername(textFieldSearchCNP.getText());
        if (tableViewBooks.getSelectionModel().getSelectedItem() != null) {
            for (var index :tableViewBooks.getSelectionModel().getSelectedIndices()){
                booksId.add(modelBooks.get(index).getBookID());
            }
            bookService.returnBooks(subscriber.getSubscriberID(),booksId);
            initModelBorrowedBooks();
        }
    }
}
