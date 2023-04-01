package com.example.hms.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UserFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblUsers;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colPasswordHInt;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtPasswordHint;

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        pane.setDisable(true);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpPage.fxml"))));
        stage.setTitle("Sign-up");
        stage.showAndWait();

        pane.setFocusTraversable(true);
        pane.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
