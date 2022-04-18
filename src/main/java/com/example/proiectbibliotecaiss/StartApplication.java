package com.example.proiectbibliotecaiss;

import com.example.proiectbibliotecaiss.business.BookService;
import com.example.proiectbibliotecaiss.business.LibrarianSerivce;
import com.example.proiectbibliotecaiss.business.SubscriberService;
import com.example.proiectbibliotecaiss.controllers.LoginController;
import com.example.proiectbibliotecaiss.repository.BookDbRepository;
import com.example.proiectbibliotecaiss.repository.LibrarianDbRepository;
import com.example.proiectbibliotecaiss.repository.SubscriberDbRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties properties=new Properties();
        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        LibrarianDbRepository librarianDbRepository=new LibrarianDbRepository(properties);
        SubscriberDbRepository subscriberDbRepository= new SubscriberDbRepository(properties);
        BookDbRepository bookDbRepository = new BookDbRepository(properties);

        LibrarianSerivce librarianSerivce=new LibrarianSerivce(librarianDbRepository);
        SubscriberService subscriberService=new SubscriberService(subscriberDbRepository);
        BookService bookService= new BookService(bookDbRepository);

        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("controllers/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        stage.setTitle("Login/Register!");
        stage.setScene(scene);
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(librarianSerivce,subscriberService,bookService);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}