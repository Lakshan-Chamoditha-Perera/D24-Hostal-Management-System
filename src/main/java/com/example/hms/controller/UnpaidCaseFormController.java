package com.example.hms.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class UnpaidCaseFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblstudents;

    @FXML
    private TableColumn<?, ?> colStdId;

    @FXML
    private TableColumn<?, ?> colStdName;

    @FXML
    private TableColumn<?, ?> colStdAddress;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    void tblstudentsOnMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
