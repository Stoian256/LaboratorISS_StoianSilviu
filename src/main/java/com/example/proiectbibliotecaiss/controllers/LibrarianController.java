package com.example.proiectbibliotecaiss.controllers;

import com.example.proiectbibliotecaiss.business.BookService;
import com.example.proiectbibliotecaiss.business.LibrarianSerivce;
import com.example.proiectbibliotecaiss.business.SubscriberService;
import com.example.proiectbibliotecaiss.domain.Librarian;

public class LibrarianController {

    private BookService bookService;
    private LibrarianSerivce librarianSerivce;
    private SubscriberService subscriberService;
    private Librarian currentLibrarian;

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
        //TO DO
    }

    private void initModel() {
        //TO DO
    }
}
