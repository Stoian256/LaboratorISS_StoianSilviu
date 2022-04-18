package com.example.proiectbibliotecaiss.controllers;

import com.example.proiectbibliotecaiss.business.BookService;
import com.example.proiectbibliotecaiss.business.LibrarianSerivce;
import com.example.proiectbibliotecaiss.business.SubscriberService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private BookService bookService;
    private LibrarianSerivce librarianSerivce;
    private SubscriberService subscriberService;

    @FXML
    private TabPane tabPaneMain;

    @FXML
    private TextField textFieldLoginUniqueCode;
    @FXML
    private PasswordField textFieldLoginPassword;
    @FXML
    private CheckBox checkAsAdmin;
    @FXML
    private Button buttonLogin;

    @FXML
    private TextField textFieldRegisterFirstName;
    @FXML
    private TextField textFieldRegisterLastName;
    @FXML
    private TextField textFieldRegisterCNP;
    @FXML
    private TextField textFieldRegisterAddress;
    @FXML
    private TextField textFieldRegisterPhone;
    @FXML
    private PasswordField textFieldRegisterPassword;
    @FXML
    private Button buttonRegister;

    @FXML
    private Label labelLoginError;
    @FXML
    private Label labelLoginErrorUniqueCode;
    @FXML
    private Label labelLoginErrorPassword;

    @FXML
    private Label labelRegisterErrorFirstName;
    @FXML
    private Label labelRegisterErrorLastName;
    @FXML
    private Label labelRegisterErrorCNP;
    @FXML
    private Label labelRegisterErrorAddress;
    @FXML
    private Label labelRegisterErrorPhone;
    @FXML
    private Label labelRegisterErrorPassword;
    @FXML
    private Label labelRegisterError;

    public void setService(LibrarianSerivce librarianSerivce,SubscriberService subscriberService,BookService bookService) {
        this.librarianSerivce=librarianSerivce;
        this.subscriberService=subscriberService;
        this.bookService=bookService;
    }

    private void initObjects() {
        initErrorLabels();
        initTextFields();
    }

    private void initErrorLabels() {
        labelLoginErrorUniqueCode.setText("");
        labelLoginErrorPassword.setText("");
        labelRegisterErrorFirstName.setText("");
        labelRegisterErrorLastName.setText("");
        labelRegisterErrorCNP.setText("");
        labelRegisterErrorAddress.setText("");
        labelRegisterErrorPhone.setText("");
        labelRegisterErrorPassword.setText("");
        labelLoginError.setText("");
        labelRegisterError.setText("");
    }

    private void initTextFields() {
        textFieldLoginUniqueCode.setText("");
        textFieldLoginPassword.setText("");
        textFieldRegisterFirstName.setText("");
        textFieldRegisterLastName.setText("");
        textFieldRegisterCNP.setText("");
        textFieldRegisterAddress.setText("");
        textFieldRegisterPhone.setText("");
        textFieldRegisterPassword.setText("");
    }

    @FXML
    public void initialize() {
        initObjects();
    }

    @FXML
    protected void onTabPaneSelectionChanged() {
        initObjects();
    }

    @FXML
    protected void onTextFieldClick() {
        initErrorLabels();
    }

    @FXML
    protected void onButtonLoginClick() {
        String username = textFieldLoginUniqueCode.getText();
        String password = textFieldLoginPassword.getText();
        if(checkAsAdmin.isSelected()) {
            boolean allGood = true;
            if (librarianSerivce.findLibrarianByUsername(username) == null) {
                labelLoginErrorUniqueCode.setText("Username does not exist!");
                allGood = false;
            } else {
                if (!librarianSerivce.findLibrarianByUsername(username).getPassword().equals(password)) {
                    labelLoginErrorPassword.setText("Incorrect password!");
                    allGood = false;
                }
            }
            if (username.equals("")) {
                labelLoginErrorUniqueCode.setText("Username can not be empty!");
                allGood = false;
            }
            if (password.equals("")) {
                labelLoginErrorPassword.setText("Password can not be empty!");
                allGood = false;
            }

            if (allGood) {
                try {
                    Stage parentStage = (Stage) buttonLogin.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("main-admin-view.fxml"));

                    Stage mainStage = new Stage();
                    mainStage.setTitle("Admin window");
                    mainStage.initModality(Modality.WINDOW_MODAL);
                    Scene scene = new Scene(loader.load(), 875, 453);
                    mainStage.setScene(scene);

                    LibrarianController mainController = loader.getController();
                    mainController.setLibrarian(librarianSerivce.findLibrarianByUsername(username));
                    mainController.setServices(librarianSerivce,subscriberService,bookService);
                    mainStage.show();
                    parentStage.close();

                } catch (IOException e) {
                    labelLoginError.setText(e.getMessage());
                    e.printStackTrace();
                }
            }
        }else {
            boolean allGood = true;
            if (subscriberService.findSubscriberByUsername(username) == null) {
                labelLoginErrorUniqueCode.setText("Username does not exist!");
                allGood = false;
            } else {
                if (!subscriberService.findSubscriberByUsername(username).getPassword().equals(password)) {
                    labelLoginErrorPassword.setText("Incorrect password!");
                    allGood = false;
                }
            }
            if (username.equals("")) {
                labelLoginErrorUniqueCode.setText("Username can not be empty!");
                allGood = false;
            }
            if (password.equals("")) {
                labelLoginErrorPassword.setText("Password can not be empty!");
                allGood = false;
            }

            if (allGood) {
                try {
                    Stage parentStage = (Stage) buttonLogin.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("main-user-view.fxml"));

                    Stage mainStage = new Stage();
                    mainStage.setTitle("User window");
                    mainStage.initModality(Modality.WINDOW_MODAL);
                    Scene scene = new Scene(loader.load(), 875, 453);
                    mainStage.setScene(scene);

                    SubscriberController mainController = loader.getController();
                    mainController.setSubscriber(subscriberService.findSubscriberByUsername(username));
                    mainController.setServices(librarianSerivce,subscriberService,bookService);
                    mainStage.show();
                    parentStage.close();

                } catch (IOException e) {
                    labelLoginError.setText(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

    }

    @FXML
    protected void onButtonRegisterClick() {
        boolean allGood = true;
        String firstName = textFieldRegisterFirstName.getText();
        String lastName = textFieldRegisterLastName.getText();
        String username = textFieldRegisterCNP.getText();
        String address = textFieldRegisterAddress.getText();
        String phone = textFieldRegisterPhone.getText();
        String password = textFieldRegisterPassword.getText();

        if (firstName.equals("")) {
            labelRegisterErrorFirstName.setText("First name can not be empty!");
            allGood = false;
        }
        if (lastName.equals("")) {
            labelRegisterErrorLastName.setText("Last name can not be empty!");
            allGood = false;
        }
        if (username.equals("")) {
            labelRegisterErrorCNP.setText("Username can not be empty!");
            allGood = false;
        }
        if (address.equals("")) {
            labelRegisterErrorAddress.setText("Address can not be empty!");
            allGood = false;
        }
        if (phone.equals("")) {
            labelRegisterErrorPhone.setText("Phone can not be empty!");
            allGood = false;
        }
        if (password.equals("")) {
            labelRegisterErrorPassword.setText("Password can not be empty!");
            allGood = false;
        }
        if (subscriberService.findSubscriberByUsername(username) != null) {
            labelRegisterErrorCNP.setText("CNP is already used!");
            allGood = false;
        }

        if (allGood) {
            subscriberService.addSubscriber(username,firstName,lastName,address,phone,password);
                initObjects();
                tabPaneMain.getSelectionModel().select(0);
        }
    }

}
